package com.spring.myApp.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class ExcelCotrollerTest {
	
	@Autowired
	private ExcelController excelController;
	
	private MockMvc mock;
	
	private Map<String, Object> data;
	private Map<String, Object> emptyData;
	
	private String jsonValue;
	private String emptyValue;
	
	@Before
	public void setUp() throws Exception {
		this.mock = MockMvcBuilders
				.standaloneSetup(excelController)
				.build();
		
		data = new HashMap<String, Object>();
		emptyData = new HashMap<String, Object>();
		
		List<LinkedHashMap<String, String>> newsList = new ArrayList<LinkedHashMap<String,String>>();
		
		data.put("selectOption", "1");
		data.put("keyword", "올림픽");
		
		LinkedHashMap<String, String> article = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> article1 = new LinkedHashMap<String, String>();
		
		article.put("newsTitle", "네이버");
		article.put("newsURL", "www.naver.com");
		
		article1.put("newsTitle", "구글");
		article1.put("newsURL", "www.google.com");
		
		newsList.add(article);
		newsList.add(article1);
		
		data.put("newsList", newsList);
		
		ObjectMapper mapper = new ObjectMapper();
		jsonValue = mapper.writeValueAsString(data);
		emptyValue = mapper.writeValueAsString(emptyData);
	}
	
	@Test
	public void 요청데이터가_존재할경우_다운로드_데이터_리턴_단위_테스트() throws Exception {
		ResponseEntity<byte[]> result = excelController.downloadExcel(emptyData);
		
		assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
	}
	
	@Test
	public void 요청데이터가_존재하지않을경우_다운로드_데이터_리턴_단위_테스트() throws Exception {
		ResponseEntity<byte[]> result = excelController.downloadExcel(data);
		
		assertEquals(HttpStatus.OK, result.getStatusCode());
		
		log.info("ContentDisposition : {}",
				URLDecoder.decode(result.getHeaders().getContentDisposition().toString(), "UTF-8"));
	}
	
	@Test
	public void 요청데이터가_존재할경우_다운로드_데이터_리턴_테스트() throws Exception {
		mock.perform(post("/downloadExcel")
				.content(jsonValue)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void 요청데이터가_존재하지않을경우_다운로드_데이터_리턴_테스트() throws Exception {
		mock.perform(post("/downloadExcel")
				.content(emptyValue)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().is2xxSuccessful())
		.andDo(print());
	}
	
	
}
