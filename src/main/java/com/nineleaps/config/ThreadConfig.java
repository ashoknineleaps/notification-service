package com.nineleaps.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class ThreadConfig {

	@Bean("notifyTask")
	public TaskExecutor getThreadPooltaskExecutor()
	{
		ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
		executor.setPoolSize(Runtime.getRuntime().availableProcessors());
		executor.setPoolSize(Runtime.getRuntime().availableProcessors());
		executor.setThreadNamePrefix("thread_Name_Prefix");
		executor.initialize();
		
		return executor;
	}
}
