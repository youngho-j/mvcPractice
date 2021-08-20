package com.spring.board.dbTest;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DBConnectionTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void DB_연결테스트() throws Exception {
		try(Connection con = dataSource.getConnection()) {
			logger.info("con : [{}]", con);
		} catch (Exception e) {
			logger.info("오류 : {}", e.getMessage());
		}
	}
}
