package com.spring.shop.persistence;


import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void DataSource_확인() throws Exception {
		try (Connection con = dataSource.getConnection()){
			log.info("con : [{}]", con);
		} catch (Exception e) {
			log.info("오류 : {}", e.getMessage());
		}
	}
	
	@Test
	public void SqlSession_확인() throws Exception {
		try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
			log.info("sqlSession : [{}]", sqlSession);
		} catch (Exception e) {
			log.info("오류 : {}", e.getMessage());			
		}
	}
}
