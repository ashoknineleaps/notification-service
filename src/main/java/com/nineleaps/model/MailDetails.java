package com.nineleaps.model;

import java.util.UUID;

public class MailDetails {

	private UUID notificationId;
	
	private String notificationTitle;
	
	private String notificationBody;

	public MailDetails() {
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
		return "MailDetails [notificationId=" + notificationId + ", notificationTitle=" + notificationTitle
				+ ", notificationBody=" + notificationBody + "]";
	}
	
}
