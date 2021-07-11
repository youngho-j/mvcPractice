package com.rebuild.test;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MyBatisTest {
	
	@Inject
	private SqlSessionFactory sqlfactory;
	
	@Test
	public void 팩토리_테스트() throws Exception {
		System.out.println("SqlSessionFactory 주소 : " + sqlfactory);
	}
	
	@Test
	public void 세션_테스트() throws Exception {
		try (SqlSession session = sqlfactory.openSession()) {
			System.out.println("SqlSession 주소 : " + session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
