package com.nineleaps.exception;

public class MessageNotFoundException extends RuntimeException
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5256720479600723737L;

	public MessageNotFoundException(String message) {
		super(message);
	}
	
}
