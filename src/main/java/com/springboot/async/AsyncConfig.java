//package com.springboot.async;
//
//import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.task.AsyncTaskExecutor;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//@EnableAsync
//@Configuration
//public class AsyncConfig {
//
//	@Bean
//	public AsyncTaskExecutor taskExecutor() {
//		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//		executor.setCorePoolSize(3);
//		return executor;
//	}
//
//	@Bean
//	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
//		return new CustomAsyncExceptionHandler();
//	}
//}
