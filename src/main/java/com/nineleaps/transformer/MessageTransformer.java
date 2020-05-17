package com.nineleaps.transformer;

import org.springframework.stereotype.Component;

import com.nineleaps.model.Message;
import com.nineleaps.model.Notification;

@Component
public class MessageTransformer implements Transformer<Message, Notification>{

	@Override
	public Notification transformer(Message message) {
		
		return new Notification(message.getId(), message.getTitle(), message.getText());
	}

}
