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

import com.spring.myApp.dto.SearchOptionDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class NaverNewsServiceTest {
	
	@Autowired
	private NaverNewsService newsService;
	
	@Test
	public void 검색옵션_및_검색어_없는경우_뉴스목록_리턴_테스트() throws Exception {
		SearchOptionDTO searchOption = new SearchOptionDTO("", "");
		
		Map<String, Object> map = newsService.getNewsList(searchOption);
		
		assertTrue(!CollectionUtils.isEmpty(map));
		assertThat(map.get("keyword"), is("올림픽"));
		assertThat(map.get("selectOption"), is("1"));
		
	}
	
	@Test
	public void 검색옵션_및_검색어_적용_뉴스목록_리턴_테스트() throws Exception {
		SearchOptionDTO searchOption = new SearchOptionDTO("0", "한국");
		
		Map<String, Object> map = newsService.getNewsList(searchOption);
		
		assertTrue(!CollectionUtils.isEmpty(map));
		assertThat(map.get("keyword"), is("한국"));
		assertThat(map.get("selectOption"), is("0"));
		
	}
	
	
}
