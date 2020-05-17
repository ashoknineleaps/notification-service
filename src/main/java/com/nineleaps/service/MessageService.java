package com.nineleaps.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.nineleaps.exception.MessageNotFoundException;
import com.nineleaps.kafka.NotificationProducer;
import com.nineleaps.model.Message;
import com.nineleaps.repository.MessageRepository;

@Service
public class MessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);

	private static final String TABLE_NALE = "MESSAGE";
	
	@Autowired
	private MessageRepository messageRepository;
	
	private RedisTemplate<String, Object> redisTemplate;
	
	private HashOperations<String, UUID, Message> hashOperations;

	@Autowired
	private NotificationProducer notificationProducer;
	
	@Autowired
	public MessageService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void initilizeHashOperation()
	{
		hashOperations = redisTemplate.opsForHash();
	}
	
	public boolean processMessage(Message message)
	{
		Message savedMessage = saveMessage(message);
		
		hashOperations.put(TABLE_NALE, message.getId(), message);
		
		LOGGER.info("Message Saved: "+savedMessage);
		
		return notificationProducer.send(message);
	}

	private Message saveMessage(Message message) {
		
		LOGGER.info("Message Details: "+message);
		message.setTime(LocalDateTime.now());
		message.setRead(false);
		
		return messageRepository.save(message);
	}
	
	public Message getMessageById(UUID messageId) throws MessageNotFoundException
	{
		Optional<Message> messageOptional = messageRepository.findById(messageId);
		
		if(!messageOptional.isPresent())
		{
			throw new MessageNotFoundException("Message not found in Message Repository");
		}
		
		Message message = hashOperations.get(TABLE_NALE, messageId);
		LOGGER.info("Get the Message from cache: "+message);
		
		return message;
	}

	public boolean updateMessageNotification(Message message, UUID messageId) {

		Optional<Message> messageOptional = messageRepository.findById(messageId);
		
		if(!messageOptional.isPresent())
		{
			throw new MessageNotFoundException("Message not found in Message Repository");
		}
		
		message.setId(messageId);
		Message updatedMessage = saveMessage(message);
		LOGGER.info("Message Updated in Message Repository: "+updatedMessage);
		
		return notificationProducer.send(message);
	}

	public Long deleteMessageById(UUID messageId) {
		
		Optional<Message> messageOptional = messageRepository.findById(messageId);
		
		if(!messageOptional.isPresent())
		{
			throw new MessageNotFoundException("Message not found in Message Repository");
		}
		
		Long deletedHashKey = hashOperations.delete(TABLE_NALE, messageId);
		LOGGER.info("Delete the Message from cache: "+deletedHashKey);

		return deletedHashKey;
	}
}
