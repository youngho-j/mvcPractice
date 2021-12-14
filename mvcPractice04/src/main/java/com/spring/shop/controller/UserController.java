package com.spring.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.shop.service.BookService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private BookService bookService;
	
	// 메인 페이지 이동
	@GetMapping({"/main", "/"})
	public String mainPageGET(Model model) throws Exception {
		log.info("메인 페이지 진입");
			
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", bookService.getDomesticCategoryCode());
		model.addAttribute("international", bookService.getInternationalCategoryCode());
			
		return "/user/main";
	}
	
	// 상품 검색
	@GetMapping("/search")
	public String SearchGoodsListGET(PageInfo pageInfo, Model model) throws Exception {
		
		List<BookVO> goodsList = bookService.getGoodsList(pageInfo);
		
		// 목록이 없을 경우
		if(goodsList.isEmpty()) {
			model.addAttribute("goodsListResult", "empty");
			return "/user/search";
		}
		
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", bookService.getDomesticCategoryCode());
		model.addAttribute("international", bookService.getInternationalCategoryCode());
		
		model.addAttribute("goodsListResult", goodsList);
		model.addAttribute("pagingManager", new PagingManager(pageInfo, bookService.getGoodsTotal(pageInfo)));
		
		return "/user/search";
	}
}
