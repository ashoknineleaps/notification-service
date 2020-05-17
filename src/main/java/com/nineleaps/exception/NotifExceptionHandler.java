package com.nineleaps.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class NotifExceptionHandler extends ResponseEntityExceptionHandler
{
	@Autowired
	private ErrorProcessor errorProcessor;
	
	@ExceptionHandler({Throwable.class})
	public final ResponseEntity<Object> handleExceptions(final Throwable ex, final WebRequest request)
	{
		return handleExceptionInternal((Exception)ex, errorProcessor.getError(ex), 
				getHeaderWithContentType(), HttpStatus.OK, request);
	}
	
	private HttpHeaders getHeaderWithContentType() 
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
	}
}
