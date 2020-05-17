package com.nineleaps.kafka;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.nineleaps.config.KafkaTopicsConfiguration;
import com.nineleaps.exception.ApplicationException;
import com.nineleaps.model.Message;
import com.nineleaps.util.JsonUtil;

@Service
public class NotificationProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	private KafkaTopicsConfiguration kafkaTopicsConfiguration;
	
	public boolean send(final Message message)
	{
		LOGGER.info("Sending Data: '{}'", message);
		
		ListenableFuture<SendResult<String,String>> listenableFuture = 
				kafkaTemplate.send(kafkaTopicsConfiguration.getNotif(), JsonUtil.INSTANCE.getJsonString(message));
		
		listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> result) {
				LOGGER.info("Send Message: '{}' with OffSet: '{}'", message, result.getRecordMetadata().offset());
			}
			
			@Override
			public void onFailure(Throwable ex) {
				LOGGER.error("Unable to send Message: '{}'", message, ex);
			}
		});
		
		SendResult<String,String> sendResult = null;
		
		try
		{
			sendResult = listenableFuture.get(60, TimeUnit.SECONDS);
		}
		catch (InterruptedException | ExecutionException | TimeoutException e) {
			throw new ApplicationException("message.sent.error", e);
		}
		
		LOGGER.info("Send Result is: '{}'", sendResult);
		
		if(sendResult != null)
		{
			return true;
		}
		
		return false;
	}
}
