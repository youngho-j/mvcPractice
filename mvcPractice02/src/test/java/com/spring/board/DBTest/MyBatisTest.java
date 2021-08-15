package com.spring.board.DBTest;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MyBatisTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Inject
	private SqlSessionFactory sqlfactory;
	
	@Test
	public void 팩토리_테스트() throws Exception {
		logger.info("SqlSessionFactory 주소 : [{}]", sqlfactory);
	}
	
	@Test
	public void 세션_테스트() throws Exception {
		try (SqlSession session = sqlfactory.openSession()) {
			logger.info("SqlSession 주소 : [{}]", session);
		} catch (Exception e) {
			logger.info("오류 : {}", e.getMessage());
		}
		
	}
}
