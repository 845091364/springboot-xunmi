package com.springboot.async;

import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {

	public static Random random = new Random();

	@Async()
	public void doTaskExceptionWithoutFuture(String para) throws Exception {
		System.out.println("开始doTaskExceptionWithoutFuture,参数:" + para);
		long start = System.currentTimeMillis();
		int a = 1 / 0;
		long end = System.currentTimeMillis();
		System.out.println("完成doTaskExceptionWithoutFuture，耗时：" + (end - start) + "毫秒");
	}

	@Async()
	public Future<String> doTaskExceptionWithFuture(String para) throws Exception {
		System.out.println("开始doTaskExceptionWithFuture,参数:" + para);
		long start = System.currentTimeMillis();
		int a = 1 / 0;
		long end = System.currentTimeMillis();
		System.out.println("完成doTaskExceptionWithFuture，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务doTaskExceptionWithFuture完成");
	}

	@Async()
	public Future<String> doTaskOne(String para) throws Exception {
		System.out.println("开始做任务一,参数:" + para);
		long start = System.currentTimeMillis();
		Thread.sleep(10000);
		long end = System.currentTimeMillis();
		System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务一完成");
	}

	@Async()
	public Future<String> doTaskTwo(String para) throws Exception {
		System.out.println("开始做任务二,参数:" + para);
		long start = System.currentTimeMillis();
		Thread.sleep(15000);
		long end = System.currentTimeMillis();
		System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务二完成");
	}

	@Async()
	public Future<String> doTaskThree(String para) throws Exception {
		System.out.println("开始做任务三,参数:" + para);
		long start = System.currentTimeMillis();
		Thread.sleep(20000);
		long end = System.currentTimeMillis();
		System.out.println("完成任务三，耗时：" + (end - start) + "毫秒");
		return new AsyncResult<>("任务三完成");
	}
}
