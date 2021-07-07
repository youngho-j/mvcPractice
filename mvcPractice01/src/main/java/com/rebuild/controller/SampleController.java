package com.rebuild.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rebuild.domain.SampleDTO;
import com.rebuild.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic ...");
	}
	
	@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get ..");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic only get ..");
	}
	
	// uri을 통해 파라미터 받아 로그 출력 테스트
	// 1. http://localhost:8080/sample/ex01?name=AAA&age=10
	// 2. uri을 통해 넘어온 값이 dto에 저장
	// 3. info 로그 출력 
	// 자동으로 타입을 변환하여 처리!!
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(" " + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		log.info("name : " + name);
		log.info("age : " + age);
		return "ex02";
	}
	
	//리스트 처리 예제
	//spring은 파라미터의 타입을 보고 객체를 생성(즉, 실제 클래스 타입으로 지정해야함)
	@GetMapping("/ex02List")
	public String ex0List2(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		return "ex02List";
	}
	
	// 배열 처리 예제
	@GetMapping("/ex02Array")
	public String ex0Array(@RequestParam("arr") String[] arr) {
		log.info("arr : " + arr);
		return "ex02Array";
	}
	
	// 객체 리스트 처리 예제
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos : " + list);
		return "ex02Bean";
	}
	
}
