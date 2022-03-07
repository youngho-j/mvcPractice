package com.spring.myApp.dto;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NewsInfoDTOTest {

	@Test
	public void DTO_생성자_테스트() {
		String newsURL = "https://www.naver.com";
		String newsTitle = "올림픽 금메달";
		
		NewsInfoDTO news 
			= new NewsInfoDTO(newsURL, newsTitle);
		
		assertThat(news.getNewsURL(), is(newsURL));
		assertThat(news.getNewsTitle(), is(newsTitle));
	}
	
	@Test
	public void jsonCreator_생성자_테스트() throws Exception{
		String json = "{\"newsURL\":\"https://www.naver.com\",\"newsTitle\":\"금메달\"}";
		
		NewsInfoDTO jsonData = new ObjectMapper()
				.readerFor(NewsInfoDTO.class)
				.readValue(json);
				
		assertEquals("금메달", jsonData.getNewsTitle());
	}
}
