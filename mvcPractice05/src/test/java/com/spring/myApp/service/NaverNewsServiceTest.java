package com.spring.myApp.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class NaverNewsServiceTest {
	
	@Autowired
	private NaverNewsService newsService;
	
	@Test
	public void 뉴스목록_리턴_테스트() throws Exception {
		
		Map<String, Object> map = newsService.getNewsList();
		
		assertTrue(!CollectionUtils.isEmpty(map));
		assertThat(map.get("keyword"), is("올림픽"));
		
	}
	
	@Test
	public void 키워드_존재하는경우_뉴스목록_리턴_테스트2() throws Exception {
		
		Map<String, Object> map = newsService.getNewsList2("축구");
		
		assertTrue(!CollectionUtils.isEmpty(map));
		assertThat(map.get("keyword"), is("축구"));
		
	}
	
	@Test
	public void 키워드_없는경우_뉴스목록_리턴_테스트3() throws Exception {
		
		Map<String, Object> map = newsService.getNewsList2("");
		
		assertTrue(!CollectionUtils.isEmpty(map));
		assertThat(map.get("keyword"), is("올림픽"));
		
	}
}
