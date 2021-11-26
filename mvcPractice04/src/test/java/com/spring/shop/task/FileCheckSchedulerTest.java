package com.spring.shop.task;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class FileCheckSchedulerTest {
	
	@Autowired
	FileCheckScheduler scheduler;
	
	@Test
	public void 스케쥴러_동작_테스트() throws Exception {
		scheduler.checkFiles();
	}

}
