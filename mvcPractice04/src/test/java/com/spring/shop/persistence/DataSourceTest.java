package com.spring.shop.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

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
	
	@Test
	public void DataSource_확인() throws Exception {
		try (Connection con = dataSource.getConnection()){
			log.info("con : [{}]", con);
		} catch (Exception e) {
			log.info("오류 : {}", e.getMessage());
		}
	}
}
