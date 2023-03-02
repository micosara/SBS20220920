package com.spring.scheduler;

//@Component("jobTask")
public class TestScheduler {
	
	//@Scheduled(fixedRate=1000*5)
	//@Scheduled(cron = "*/5 * * * * * ")
	public void testMessage() {
		System.out.println("안녕하세요.!!");
	}
}
