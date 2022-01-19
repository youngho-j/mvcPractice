package com.spring.shop.util;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
public class PathManagerTest {

	private PathManager pathManager;
	
	private DateTimeFormatter dateFormat;
	
	@Before
	public void setUp() throws Exception {
		pathManager = new PathManager();
		
		dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}
	
	@Test
	public void getNowPath_리턴_테스트() throws Exception {
		
		String exceptedResult = LocalDate.now()
				.format(dateFormat)
				.replaceAll("-", Matcher.quoteReplacement(File.separator));
		
		String result = pathManager.getNowPath();
		
		log.info("변환 결과 : " + result);
		
		assertThat(result, is(exceptedResult));
	}
	
	@Test
	public void getTheDayBeforePath_리턴_테스트() throws Exception {
		
		String exceptedResult = LocalDate.now().minusDays(1)
				.format(dateFormat)
				.replaceAll("-", Matcher.quoteReplacement(File.separator));
		
		String result = pathManager.getTheDayBeforePath();
		
		log.info("변환 결과 : " + result);
		
		assertThat(result, is(exceptedResult));
	}
	
	
}
