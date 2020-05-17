package com.nineleaps.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class MailDetails {

	private UUID messageId;
	
	private String messageTitle;
	
	private String messagetext;
	
	private String messageUrl;
	
	private LocalDateTime messageTime;

	private UUID notificationId;
	
	private String notificationTitle;
	
	private String notificationBody;

	public MailDetails() {
	}
	
	public UUID getMessageId() {
		return messageId;
	}

	public void setMessageId(UUID messageId) {
		this.messageId = messageId;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}

	public String getMessageUrl() {
		return messageUrl;
	}

	public void setMessageUrl(String messageUrl) {
		this.messageUrl = messageUrl;
	}

	public LocalDateTime getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(LocalDateTime messageTime) {
		this.messageTime = messageTime;
	}

	public UUID getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(UUID notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public String getNotificationBody() {
		return notificationBody;
	}

	public void setNotificationBody(String notificationBody) {
		this.notificationBody = notificationBody;
	}

	@Override
	public String toString() {
		return "MailDetails [messageId=" + messageId + ", messageTitle=" + messageTitle + ", messagetext=" + messagetext
				+ ", messageUrl=" + messageUrl + ", messageTime=" + messageTime + ", notificationId=" + notificationId
				+ ", notificationTitle=" + notificationTitle + ", notificationBody=" + notificationBody + "]";
	}
	
}
