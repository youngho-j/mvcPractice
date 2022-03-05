package com.spring.myApp.util;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelUtilTest {
	
	private ExcelUtil excel;
	
	private Map<String, Object> data;
	
	@Before
	public void setUp() {
		excel = new ExcelUtil();
		
		data = new HashMap<String, Object>();
		
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
	}
	
	@Test
	public void 엑셀_시트_생성_테스트() throws Exception {
		
		Workbook workbook = excel.makeExcelFile(data);
		
		assertFalse(workbook.getNumberOfSheets() == 0);
		assertTrue(workbook.getSheetAt(0).getLastRowNum() > 0);
	}
	
	@Test
	public void 엑셀_파일_이름_생성_테스트() throws Exception {
		String fileName = excel.makeExcelFileName(data);
		
		log.info("파일 이름 [{}]", fileName);
		
		assertThat(fileName.length(), is(18));
	}
}
