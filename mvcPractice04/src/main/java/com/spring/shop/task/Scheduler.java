package com.spring.shop.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Scheduler {
	@Scheduled(cron = "0/5 * * * * *")
	public void printInfo() {
		log.warn("스케쥴러 실행");
		log.warn("==========");
	}
}
