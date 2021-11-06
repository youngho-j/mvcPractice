package com.spring.shop.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FolderManager {
	
	private final File file;
	
	public FolderManager(String uploadRoot) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String stringDate = dateFormat.format(new Date());
		
		// 윈도우에서 File.separator 사용시 \\ 을 -> \ 로 리턴하여 character to be escaped is missing 에러 발생
		// 따라서 Matcher 클래스의 quoteReplacement(String s)를 사용
		String detailRoot = stringDate.replaceAll("-", Matcher.quoteReplacement(File.separator));
		
		this.file = new File(uploadRoot, detailRoot);
	}
	
	public boolean createFolder() throws Exception{
		if(!file.exists()) {
			log.info("폴더 생성");
			return file.mkdirs();
		}
		log.info("이미 폴더가 생성되었습니다.");
		return false;
	}
}
