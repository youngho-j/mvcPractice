package com.spring.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	
	// 메인 페이지 이동
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String mainPageGET() throws Exception {
		log.info("메인 페이지 진입");
		
		return "main";
	}
}
