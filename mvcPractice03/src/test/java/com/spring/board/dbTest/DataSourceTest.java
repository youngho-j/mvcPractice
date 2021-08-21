package com.spring.board.dbTest;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void Mybatis_sqlSession_테스트() throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		try(sqlSession) {
			assertNotNull(sqlSession);
			logger.info("SqlSession 주소 : [{}]", sqlSession);
		} catch (Exception e) {
			logger.info("오류 : {}", e.getMessage());
		}
			
	}
}
