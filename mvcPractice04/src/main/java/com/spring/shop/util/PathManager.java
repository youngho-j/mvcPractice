package com.spring.shop.util;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PathManager {
	
	private DateTimeFormatter dateformat;

	public PathManager() {
		this.dateformat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

	public String getNowPath() {
		log.info("가변경로 생성[금일 기준]");
		
		String variationPath = LocalDate.now().format(dateformat);
		
		return variationPath.replaceAll("-", Matcher.quoteReplacement(File.separator));
		
	}
	
	public String getTheDayBeforePath() {
		log.info("가변경로 생성[전일 기준]");
		
		LocalDate beforeDay = LocalDate.now().minusDays(1);
		
		String variationPath = beforeDay.format(dateformat);
		return variationPath.replaceAll("-", Matcher.quoteReplacement(File.separator));
	}
}
