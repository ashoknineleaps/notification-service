package com.nineleaps.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.nineleaps.exception.MessageNotFoundException;
import com.nineleaps.model.Message;
import com.nineleaps.service.MessageService;
import com.nineleaps.util.ValidatorUtil;
import com.nineleaps.validators.MessageValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Message Notification RESTful Service", value = "MessageController", description = "Controoller for the Message Notification Service")
@RestController
@RequestMapping(value = "/api/v1/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private MessageValidator messageValidator;
	
	@Autowired 
	private ValidatorUtil validationUtils;
	
	@ApiOperation(value = "Send a Message Notification", response = Message.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Retrieved"),
			@ApiResponse(code = 201, message = "Message Notification Created Successfully"),
			@ApiResponse(code = 401, message = "You are Unauthenticated to view the resource"),
			@ApiResponse(code = 403, message = "Unauthorized, doesn't have enough privilege to view the resource"),
			@ApiResponse(code = 404, message = "The resource you are trying too reach is not found")})
	@PostMapping
	public ResponseEntity<Boolean> sendMessageNotification(@RequestBody Message message)
	{
		validationUtils.validate(messageValidator, message, "message");
		
		boolean messageSaved = messageService.processMessage(message);
		
		return new ResponseEntity<>(messageSaved, HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Retrieve Message by id", response = Message.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Retrieved"),
			@ApiResponse(code = 401, message = "You are Unauthenticated to view the resource"),
			@ApiResponse(code = 403, message = "Unauthorized, doesn't have enough privilege to view the resource"),
			@ApiResponse(code = 404, message = "The resource you are trying too reach is not found")})
	@Cacheable(key = "#messageId", value = "messages")
	@GetMapping("/{messageId}")
	public Message getMessageById(@PathVariable UUID messageId)
	{
		try 
		{
			Message message = messageService.getMessageById(messageId);
			return message;
			
		} catch (MessageNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@ApiOperation(value = "Update the Message Notification", response = Message.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Retrieved"),
			@ApiResponse(code = 401, message = "You are Unauthenticated to view the resource"),
			@ApiResponse(code = 403, message = "Unauthorized, doesn't have enough privilege to view the resource"),
			@ApiResponse(code = 404, message = "The resource you are trying too reach is not found")})
	@CachePut(key = "#messageId", value = "messages")
	@PutMapping("/{messageId}")
	public boolean updateMessageNotification(@RequestBody Message message, @PathVariable UUID messageId)
	{
		validationUtils.validate(messageValidator, message, "message");

		return messageService.updateMessageNotification(message, messageId);
	}
	
	@ApiOperation(value = "Delete the Message by id", response = Message.class)
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Retrieved"),
			@ApiResponse(code = 401, message = "You are Unauthenticated to view the resource"),
			@ApiResponse(code = 403, message = "Unauthorized, doesn't have enough privilege to view the resource"),
			@ApiResponse(code = 404, message = "The resource you are trying too reach is not found")})
	@CacheEvict(key = "#messageId", value = "messages")
	@DeleteMapping("/{messageId}")
	public Long deleteMessageById(@PathVariable UUID messageId)
	{
		return messageService.deleteMessageById(messageId);
	}
}
