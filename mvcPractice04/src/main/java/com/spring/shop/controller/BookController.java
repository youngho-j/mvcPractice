package com.spring.shop.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.shop.service.BookService;
import com.spring.shop.service.FileService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private BookService bookService;
	
	// 메인 페이지 이동
	@GetMapping({"/main", "/"})
	public String mainPageGET(Model model) throws Exception {
		log.info("메인 페이지 진입");
		
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", bookService.getDomesticCategoryCode());
		model.addAttribute("international", bookService.getInternationalCategoryCode());
		
		return "main";
	}
	
	// 이미지 호출
	@GetMapping("/display")
	public ResponseEntity<byte[]> showImageGET(String fileName) throws Exception {
		log.info("이미지 파일 출력");
		
		HttpHeaders header = new HttpHeaders();
		
		File file = new File("H:\\mvcPractice04upload", fileName);
		
		// 파일 MIME TYPE 추가
		header.add("Content-type", Files.probeContentType(file.toPath()));
		
		// http response body에 파일을 복사한 바이트 배열, MIME TYPE을 담은 헤더, 상태코드를 담아 리턴
		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		
	}
	
	// 이미지 정보 리턴
	@GetMapping(value = "/getImageInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImageInfoVO>> ImageInfoGET(int bookId) throws Exception {
		log.info("이미지 정보 리턴");
		
		return new ResponseEntity<List<ImageInfoVO>>(fileService.getImageList(bookId), HttpStatus.OK);
	}
	
	// 상품 검색
	@GetMapping("search")
	public String SearchGoodsListGET(PageInfo pageInfo, Model model) throws Exception {
		
		List<BookVO> goodsList = bookService.getGoodsList(pageInfo);
		
		// 목록이 없을 경우
		if(goodsList.isEmpty()) {
			model.addAttribute("goodsListResult", "empty");
			return "search";
		}
		
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", bookService.getDomesticCategoryCode());
		model.addAttribute("international", bookService.getInternationalCategoryCode());
		
		model.addAttribute("goodsListResult", goodsList);
		model.addAttribute("pagingManager", new PagingManager(pageInfo, bookService.getGoodsTotal(pageInfo)));
		return "search";

	}
}
