package com.spring.myApp.dto;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class NewsInfoDTOTest {

	@Test
	public void DTO_테스트() {
		String newsURL = "https://www.naver.com";
		String newsTitle = "올림픽 금메달";
		
		NewsInfoDTO news 
			= new NewsInfoDTO(newsURL, newsTitle);
		
		assertThat(news.getNewsURL(), is(newsURL));
		assertThat(news.getNewsTitle(), is(newsTitle));
	}

}
