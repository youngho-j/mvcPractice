package com.spring.shop.task;

import static org.junit.Assert.*;
import static java.time.Duration.*;
import static java.util.concurrent.TimeUnit.*;
import static org.hamcrest.Matchers.*;
import static org.awaitility.Awaitility.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;

import org.awaitility.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class SchedulerTest {

	@Test
	public void 스케쥴러_동작_테스트() {
		Scheduler service = new Scheduler();
		
		
	}

}
