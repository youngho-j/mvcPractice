package com.spring.myApp.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myApp.dto.SearchOptionDTO;
import com.spring.myApp.service.NewsService;

@RestController
public class NewsController {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	
	private NewsService newsService;
	
	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@PostMapping(value = "/crawling")
	public ResponseEntity<Map<String, Object>> newsCrawl(SearchOptionDTO searchOption) throws IOException {
		
		Map<String, Object> result = newsService.getNewsList(searchOption);
		
		if(CollectionUtils.isEmpty(result)) {
			logger.info("결과값이 존재하지 않습니다.");
			return new ResponseEntity<>(new HashMap<>(), HttpStatus.NO_CONTENT);				
		}
		
		logger.info("결과값이 존재합니다.");
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
