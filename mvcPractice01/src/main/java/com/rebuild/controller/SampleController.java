package com.rebuild.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rebuild.domain.SampleDTO;
import com.rebuild.domain.SampleDTOList;
import com.rebuild.domain.TodoDTO;
import com.rebuild.domain.TodoDTO2;

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
	
	// DTO 데이터 출력 예제
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(" " + dto);
		return "ex01";
	}
	
	// 파라미터 수집 후 자동으로 타입 변환하여 데이터 출력 예제
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dataFormat, false));
	}
	
	// @InitBinder 사용하여 문자열 타입의 데이터를 Date 타입으로 변환하는 예제
	@GetMapping("ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo : " + todo);
		return "ex03";
	}
	
	// 위 예제와 같은 결과 - DTO에서 @DateTimeFormat으로 변환
	@GetMapping("ex03Format")
	public String ex03Format(TodoDTO2 todo) {
		log.info("todo2 : " + todo);
		return "ex03Format";		
	}
	
	// @ModelAttribute 사용하여 int 타입 화면에 출력하기 
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("dto : " + dto);
		log.info("page : " + page);
		return "/sample/ex04"; 
	}
	
	// Controller void 리턴 타입
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05..");
	}
	
	// Controller String 리턴 타입 - redirect 방식, forward 방식으로 처리 가능
	// redirect 방식, forward 방식 알아보기 - https://doublesprogramming.tistory.com/63
	@GetMapping("/ex06")
	public String ex06() {
		log.info("/ex06..");
		// 현재 view 페이지 구성은 아래와 같음
		// sample
		//   ex04.jsp
		// home.jsp
		return "/home";
	}
	
	// Controller 객체 리턴 타입 - JSON 데이터 만들기 용도
	// 3버전이라 jackson-databind 라이브러리 추가 필요
	@GetMapping("/ex07")
	public @ResponseBody SampleDTO ex07() {
		log.info("/ex07..");
		SampleDTO dto = new SampleDTO();
		dto.setName("김기자");
		dto.setAge(12);
		return dto;
	}
	
	
}
