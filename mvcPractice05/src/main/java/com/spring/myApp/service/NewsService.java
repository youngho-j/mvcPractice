package com.spring.myApp.service;

import java.io.IOException;
import java.util.Map;

import com.spring.myApp.dto.SearchOptionDTO;

public interface NewsService {
	
	Map<String, Object> getNewsList(SearchOptionDTO searchOption) throws IOException;
}
