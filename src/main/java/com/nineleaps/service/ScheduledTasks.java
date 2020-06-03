package com.nineleaps.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	private static final LocalDate dateFormat = LocalDate.now();

	@Autowired
	private EmailNotificationService emailNotificationService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Scheduled(fixedRate = 1000 * 60)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat);
		
			emailNotificationService.sendMail();
	}
}
