package com.spring.myApp.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.spring.myApp.dto.NewsInfoDTO;
import com.spring.myApp.dto.SearchOptionDTO;
import com.spring.myApp.util.KeywordCheck;
import com.spring.myApp.util.SelectOptionCheck;

enum NewsCount {
	
	ONE(1),
	TEN(10),
	TWENTY(20),
	THIRTY(30);
	
	private final int value;
	
	NewsCount(int value) {this.value = value;}
	
	public int getValue() {return value;}
}

@Service
public class NaverNewsService implements NewsService {
	
	private static final Logger logger = LoggerFactory.getLogger(NaverNewsService.class);
	
	@Override
	public Map<String, Object> getNewsList(SearchOptionDTO searchOption) throws IOException {
		KeywordCheck keywordCheck = new KeywordCheck();
		SelectOptionCheck optionCheck = new SelectOptionCheck();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<NewsInfoDTO> newsList = new ArrayList<>();
		
		String query = keywordCheck.convertValue(searchOption.getKeyword());
		
		String selectOption = optionCheck.convertValue(searchOption.getSelectOption());
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");
		
		String today = LocalDate.now().format(dateFormat);
		
		String yesterday = LocalDate.now().minusDays(1).format(dateFormat);
		
		map.put("keyword", keywordCheck.restoreValue(query));
		
		map.put("selectOption", selectOption);
		
		int start = NewsCount.ONE.getValue();
		
		while(start < NewsCount.TWENTY.getValue()) {
			
			String newsURL = 
					"https://search.naver.com/search.naver?"
							+ "where=news&query=" + query
							+ "&sm=tab_opt"
							+ "&sort=" + selectOption
							+ "&photo=0&field=0&pd=3"
							+ "&ds=" + yesterday
							+ "&de=" + today
							+ "&docid=&related=0&mynews=0&office_type=0&office_section_code=0&news_office_checked="
							+ "&nso=so:dd,p:from" + yesterday.replaceAll(".", "")
							+ "to" + today.replaceAll(".", "") 
							+ "&is_sug_officeid=0"
							+ "&start=" + Integer.toString(start);
			
			Document rawDoc 
			= Jsoup.connect(newsURL)
			.userAgent("Mozilla/5.0 (Windows NT 10.0; win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.82 Safari/537.36")
			.header("Accept", "text/html")
			.header("Accept-Encoding", "gzip,deflate")
			.header("Accept-Language", "it-IT,en;q=0.8,en-US;q=0.6,de;q=0.4,it;q=0.2,es;q=0.2")
			.header("Connection", "keep-alive")
			.ignoreContentType(true)
			.timeout(5000).get();
			
			Elements elements = rawDoc.getElementsByClass("bx");
			
			for(Element element : elements) {
				
				String link = element.getElementsByClass("news_tit").attr("href");
				String title = element.getElementsByClass("news_tit").attr("title");
				
				if(!link.isEmpty() && !title.isEmpty()) {
					newsList.add(new NewsInfoDTO(link, title));
				}
			}
			
			start += NewsCount.TEN.getValue();
		}
		
		if(CollectionUtils.isEmpty(newsList)) {
			logger.info("목록 크롤링 실패");
			return new HashMap<>();				
		}
		
		logger.info("목록 크롤링 성공");
		map.put("newsList", newsList);
		return map;
	}
}
