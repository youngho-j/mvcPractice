package com.spring.myApp.service;

import java.io.IOException;
import java.util.Map;

public interface NewsService {
	
	public Map<String, Object> getNewsList() throws IOException;

}
