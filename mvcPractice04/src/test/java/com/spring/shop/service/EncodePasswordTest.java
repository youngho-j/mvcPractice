package com.spring.shop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class EncodePasswordTest {
	
	@Autowired
	private EncodePassword encodePassword;
	
	@Test
	public void 비밀번호_암호화_테스트() throws Exception {
		String password = "12314";
		
		String encoding = encodePassword.EncodingPassword(password);
		
		log.info(encoding);
		
		assertNotSame(password, encoding);
	}
	
	@Test
	public void 비밀번호_비교_테스트() throws Exception {
		
		String password = "12314";
		
		String encoding = encodePassword.EncodingPassword(password);
		
		boolean result = encodePassword.comparePassword(password, encoding);
		
		assertTrue(result);
	}

}
