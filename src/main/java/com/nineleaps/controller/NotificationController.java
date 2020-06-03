//package com.nineleaps.controller;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.nineleaps.exception.NotificationNotFoundException;
//import com.nineleaps.model.Notification;
//import com.nineleaps.service.NotificationService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
//
//@Api(tags = "Notification RESTful Service", value = "NotificationController", description = "Controoller for the Notification Service")
//@RestController
//@RequestMapping(value = "/api/v1/notifications")
//public class NotificationController {
//	
//	@Autowired
//	private NotificationService notificationService;
//	
//	@ApiOperation(value = "Retrieve Notification by id", response = Notification.class)
//	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Retrieved"),
//			@ApiResponse(code = 401, message = "You are Unauthenticated to view the resource"),
//			@ApiResponse(code = 403, message = "Unauthorized, doesn't have enough privilege to view the resource"),
//			@ApiResponse(code = 404, message = "The resource you are trying too reach is not found")})	
//	@Cacheable(key = "#notificationId", value = "notifications")
//	@GetMapping("/{notificationId}")
//	public Notification getNotificationById(@PathVariable UUID notificationId)
//	{
//		try
//		{
//			return notificationService.getNotificationById(notificationId);
//		}
//		catch(NotificationNotFoundException e)
//		{
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//		}
//	}
//	
//	@ApiOperation(value = "Delete the Notification by id", response = Notification.class)
//	@ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Retrieved"),
//			@ApiResponse(code = 401, message = "You are Unauthenticated to view the resource"),
//			@ApiResponse(code = 403, message = "Unauthorized, doesn't have enough privilege to view the resource"),
//			@ApiResponse(code = 404, message = "The resource you are trying too reach is not found")})
//	@CacheEvict(key = "#notificationId", value = "notifications")
//	@DeleteMapping("/{notificationId}")
//	public Long deleteNotificationById(@PathVariable UUID notificationId)
//	{
//		return notificationService.deleteNotificationById(notificationId);
//	}
//}
