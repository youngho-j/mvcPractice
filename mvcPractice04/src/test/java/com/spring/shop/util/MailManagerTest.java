package com.spring.shop.util;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class MailManagerTest {

	private MailManager mailManager;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Test
	public void 메일_전송_테스트() throws Exception {
		mailManager = new MailManager(mailSender);
		
		String num = mailManager.sendAuthEmail("alfkwl1239@naver.com");
		
		log.info("인증번호 : " + num);
		
		assertThat(6, is(num.length()));
	}
	
}
