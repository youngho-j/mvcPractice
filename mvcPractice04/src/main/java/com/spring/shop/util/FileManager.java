package com.spring.shop.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileManager {
	
	private final File file;
	
	@Getter
	private final String Absolutepath;
	
	public FileManager(String uploadRoot) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String stringDate = dateFormat.format(new Date());
		
		// 윈도우에서 File.separator 사용시 \\ 을 -> \ 로 리턴하여 character to be escaped is missing 에러 발생
		// 따라서 Matcher 클래스의 quoteReplacement(String s)를 사용
		String detailRoot = stringDate.replaceAll("-", Matcher.quoteReplacement(File.separator));
		
		this.file = new File(uploadRoot, detailRoot);
		this.Absolutepath = this.file.getAbsolutePath();
	}
	
	public boolean createFolder() throws Exception {
		if(!file.exists()) {
			log.info("폴더 생성");
			return file.mkdirs();
		}
		log.info("이미 폴더가 생성되었습니다.");
		return false;
	}
	
	public List<String> transferToFolder(MultipartFile[] multipartFile, String uploadRoot) throws Exception {
		
		List<String> fileNameList = new ArrayList<String>();
		
		StringBuilder sb = new StringBuilder();
		
		for(MultipartFile file : multipartFile) {
			String uploadFileName = file.getOriginalFilename();
			
			// 파일 이름 중복을 막기위해 UUID 사용
			String uuid = UUID.randomUUID().toString();
			
			sb.append(uuid);
			sb.append("_");
			sb.append(uploadFileName);
			
			uploadFileName = sb.toString();
			
			// 변경된 파일 이름과 해당년월일자 폴더 경로를 갖는 file 객체 생성
			File saveFile = new File(uploadRoot, uploadFileName);
			
			// 수신 파일 객체(file)를 목적지 파일 객체(savefile)로 전달하여 저장
			file.transferTo(saveFile);
			
			fileNameList.add(uploadFileName);
			
			log.info("파일 저장 완료!");
			
			// 길이를 0으로 설정하여 StringBuilder 초기화 
			sb.setLength(0);
			
		}
		
		return fileNameList;
	}
}
