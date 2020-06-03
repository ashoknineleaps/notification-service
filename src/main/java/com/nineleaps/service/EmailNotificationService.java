package com.nineleaps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nineleaps.model.MailDetails;
import com.nineleaps.model.Notification;
import com.nineleaps.repository.MessageRepository;
import com.nineleaps.repository.NotificationRepository;

@Service
public class EmailNotificationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationService.class);

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	private NotificationRepository notificationRepository;

	@Value("${spring.mail.username}")
	private String senderEmailId;
	
	@Async("notifyTask")
	public void sendMail()
	{
		for(Notification notification : notificationRepository.findAll())
		{
			MailDetails mailDetails = new MailDetails();
			mailDetails.setNotificationId(notification.getId());
			mailDetails.setNotificationTitle(notification.getTitle());
			mailDetails.setNotificationBody(notification.getBody());
			
			LOGGER.info("Your Mail is Sent: "+mailDetails.toString());
			
			notificationRepository.deleteAll();
			
		}
		//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		
//		mailDetails.getNotificationId();
//		String firstName = "Ashok";
//		
//		simpleMailMessage.setTo("ashokcse126@gmail.com");
//		simpleMailMessage.setFrom(senderEmailId);
//		simpleMailMessage.setSubject("Message Notification");
//		//simpleMailMessage.setText("Testing");
//		simpleMailMessage.setText("Dear" + firstName + "\nMessage Notification successfully Received" + " and Message Deatils is: \n"+
//		"Message Id: "+mailDetails.getMessageId()+"\nMessage Title: "+mailDetails.getMessageTitle()+"\nMessage Text: "+mailDetails.getMessagetext()+
//		"\nMessage Url: "+mailDetails.getMessageUrl()+"\nMessage Time: "+mailDetails.getMessageTime()+
//		"\nAnd Notification Deatils: "+"\nNotification Id: "+mailDetails.getNotificationId()+"\nNotification Title: "+mailDetails.getNotificationTitle()+"\nNotification Body: "+mailDetails.getNotificationBody()+"\nRegards");
//		
//		javaMailSender.send(simpleMailMessage);
		
	}
	
//	public void sendMail(UUID messageId, UUID notificationId)
//	{
//		Optional<Message> messageOptional = messageRepository.findById(messageId);
//		
//		if(!messageOptional.isPresent())
//		{
//			throw new MessageNotFoundException("Message not found in Message Repository");
//		}
//		
//		Message message = messageOptional.get();
//		
//		Optional<Notification> notificationOptional = notificationRepository.findById(notificationId);
//		
//		if(!notificationOptional.isPresent())
//		{
//			throw new NotificationNotFoundException("Notification not found in Notification Repository");
//		}
//		
//		Notification notification = notificationOptional.get();
//		
//		MailDetails mailDetails = new MailDetails();
//		mailDetails.setMessageId(message.getId());
//		mailDetails.setMessageTitle(message.getTitle());
//		mailDetails.setMessagetext(message.getText());
//		mailDetails.setMessageUrl(message.getUrl());
//		mailDetails.setMessageTime(message.getTime());
//		
//		mailDetails.setNotificationId(notification.getId());
//		mailDetails.setNotificationTitle(notification.getTitle());
//		mailDetails.setNotificationBody(notification.getBody());
//		
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		
//		mailDetails.getNotificationId();
//		String firstName = "Ashok";
//		
//		simpleMailMessage.setTo("ashokcse126@gmail.com");
//		simpleMailMessage.setFrom(senderEmailId);
//		simpleMailMessage.setSubject("Message Notification");
//		//simpleMailMessage.setText("Testing");
//		simpleMailMessage.setText("Dear" + firstName + "\nMessage Notification successfully Received" + " and Message Deatils is: \n"+
//		"Message Id: "+mailDetails.getMessageId()+"\nMessage Title: "+mailDetails.getMessageTitle()+"\nMessage Text: "+mailDetails.getMessagetext()+
//		"\nMessage Url: "+mailDetails.getMessageUrl()+"\nMessage Time: "+mailDetails.getMessageTime()+
//		"\nAnd Notification Deatils: "+"\nNotification Id: "+mailDetails.getNotificationId()+"\nNotification Title: "+mailDetails.getNotificationTitle()+"\nNotification Body: "+mailDetails.getNotificationBody()+"\nRegards");
//		
//		javaMailSender.send(simpleMailMessage);
//		
//		
//		LOGGER.info("Your Mail is Sent");
//	}
}
