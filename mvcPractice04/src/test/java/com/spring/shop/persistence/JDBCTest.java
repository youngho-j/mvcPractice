package com.spring.shop.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
public class JDBCTest {
	
	@Value("#{dbProperty['jdbc.url']}")
	private String url;
	@Value("#{dbProperty['jdbc.userName']}")
	private String userName;
	@Value("#{dbProperty['jdbc.password']}")
	private String password;
	
	@Test
	public void properties_읽어오기() throws Exception {
		assertThat("root", is(userName));
	}
	
	@Test
	public void DB_연결_확인() throws Exception {
		try (Connection con = 
				DriverManager.getConnection(url, userName, password)){
			log.info("con : [{}]", con);
		} catch (Exception e) {
			log.info("오류 : {}", e.getMessage());
		}
	}
}
