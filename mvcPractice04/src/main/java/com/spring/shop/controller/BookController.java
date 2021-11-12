package com.spring.shop.controller;

import java.io.File;
import java.nio.file.Files;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	
	// 메인 페이지 이동
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gomainPageGET() throws Exception {
		log.info("메인 페이지 진입");
		
		return "main";
	}
	
	// 메인 페이지 이동
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void mainPageGET() throws Exception {
		log.info("메인 페이지 진입");
	}
	
	// 이미지 호출
	@GetMapping("/display")
	public ResponseEntity<byte[]> showImageGET(String fileName) throws Exception {
		log.info("이미지 파일 출력");
		
		HttpHeaders header = new HttpHeaders();
		
		// 파일 객체 생성
		// goodsEnroll 페이지에서 파일 루트 생성 및 파일 복사 후 전체 이름을 가져옴
		// 기존 테스트를 위해 fileName 앞에 고정 폴더 경로를 입력함으로 인해 고정경로가 두번 출력됨
		File file = new File(fileName);
		
		// 파일 MIME TYPE 추가
		header.add("Content-type", Files.probeContentType(file.toPath()));
		
		// http response body에 파일을 복사한 바이트 배열, MIME TYPE을 담은 헤더, 상태코드를 담아 리턴
		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		
	}
}
