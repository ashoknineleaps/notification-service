package com.nineleaps.util;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public enum JsonUtil {

	INSTANCE;
	
	public String getJsonString(Object object)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			return mapper.writeValueAsString(object);
		}
		catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public <T> T getObject(String jsonString, Class<T> clazz) {
		
		if(StringUtils.isEmpty(jsonString))
		{
			throw new IllegalArgumentException("Input Argument can not be Empty!!!");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.registerModule(new Jdk8Module());
			mapper.registerModule(new JavaTimeModule());
			T readValue = mapper.readValue(jsonString, clazz);
			
			return readValue;
			
		}catch (IOException e) {
			
			throw new RuntimeException(e);
		}
	}
}
