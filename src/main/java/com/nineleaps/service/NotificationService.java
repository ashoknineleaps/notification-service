package com.nineleaps.service;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nineleaps.exception.NotificationNotFoundException;
import com.nineleaps.model.Message;
import com.nineleaps.model.Notification;
import com.nineleaps.repository.NotificationRepository;
import com.nineleaps.transformer.MessageTransformer;

@Service
public class NotificationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

	private static final String TABLE_NAME = "NOTIFICATION";
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private MessageTransformer messageTransformer;
	
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private EmailNotificationService emailNotificationService;
	
	private HashOperations<String, UUID, Notification> hashOperations;
	
	@Autowired
	public NotificationService(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@PostConstruct
	public void initilizeHashOperation()
	{
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Async
	public Notification triggerNotification(Message message)
	{
		Notification notification = saveNotification(message);
		
		return notification;
	}

	public Notification saveNotification(Message message) {
		
		Notification notification = messageTransformer.transformer(message);
		
		LOGGER.info("Notification: "+ notification);

		Notification savedNotification = notificationRepository.save(notification);
		
		hashOperations.put(TABLE_NAME, savedNotification.getId(), savedNotification);
		
		LOGGER.info("Saved Notification: "+savedNotification);
		

		//Email Notification Logic
		//emailNotificationService.sendMail(message.getId(), savedNotification.getId());
		
		return savedNotification;
	}
	
	public Notification getNotificationById(UUID notificationId) throws NotificationNotFoundException
	{
		Optional<Notification> messageOptional = notificationRepository.findById(notificationId);
		
		if(!messageOptional.isPresent())
		{
			throw new NotificationNotFoundException("Notification not found in Notification Repository");
		}
		
		Notification notification = hashOperations.get(TABLE_NAME, notificationId);
		LOGGER.info("Get the Notification from cache: "+notification);

		return notification;
	}

	public Long deleteNotificationById(UUID notificationId) {

		Optional<Notification> messageOptional = notificationRepository.findById(notificationId);
		
		if(!messageOptional.isPresent())
		{
			throw new NotificationNotFoundException("Notification not found in Notification Repository");
		}
		
		Long deletedHashKey = hashOperations.delete(TABLE_NAME, notificationId);
		LOGGER.info("Deleted the Notification from cache: "+deletedHashKey);

		return deletedHashKey;
	}
	
}
