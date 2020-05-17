package com.nineleaps.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.nineleaps.model.Message;
import com.nineleaps.service.NotificationService;
import com.nineleaps.util.JsonUtil;

@Service
public class NotificationConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@KafkaListener(topics = "${kafka.topic.notif}")
	public void receiveNotification(ConsumerRecord<?, ?> consumerRecord)
	{
		LOGGER.info("Received Payload: '{}'", consumerRecord.toString());
		
		Message message = JsonUtil.INSTANCE.getObject(consumerRecord.value().toString(), Message.class);
		
		notificationService.saveNotification(message);
	}
}
