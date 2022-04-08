package com.spring.shop.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.shop.service.BookSearchService;
import com.spring.shop.service.CategoryService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookSearchController {
	
	private BookSearchService bookSearchService;
	
	private CategoryService categoryService;
	
	@Autowired
	public BookSearchController(BookSearchService bookSearchService, CategoryService categoryService) {
		this.bookSearchService = bookSearchService;
		this.categoryService = categoryService;
	}

	@GetMapping("/search")
	public String SearchGoodsListGET(PageInfo pageInfo, Model model) throws Exception {
		log.info("[전체 유저] 상품 검색 페이지 이동");
		
		List<BookVO> goodsList = bookSearchService.getGoodsList(pageInfo);
		
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", categoryService.getDomesticCategoryCode());
		model.addAttribute("international", categoryService.getInternationalCategoryCode());
		
		// 목록이 없을 경우
		if(goodsList.isEmpty()) {
			model.addAttribute("goodsListResult", "empty");
			return "user/search";
		}
		
		model.addAttribute("goodsListResult", goodsList);
		model.addAttribute("pagingManager", new PagingManager(pageInfo, bookSearchService.getGoodsTotal(pageInfo)));
		
		return "user/search";
	}
	
	@RequestMapping(value = "/admin/goodsManage", method = RequestMethod.GET)
	public void goodsManageGET(PageInfo pageInfo, Model model) throws Exception {
		log.info("[관리자 상품 페이지] 상품 목록 페이지 이동");
		
		// 상품 목록 데이터
		List<BookVO> list = bookSearchService.adminPageGoodsList(pageInfo);
		
		if(!list.isEmpty()) {
			model.addAttribute("list", list);
			// 페이징 관련 정보	
			model.addAttribute("pagingManager", new PagingManager(pageInfo, bookSearchService.adminPageGoodsTotal(pageInfo)));
			return;
		} else {
			model.addAttribute("checkResult", "empty");
			return;
		}
		
	}
}
