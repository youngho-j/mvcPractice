package com.spring.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.shop.service.BookService;
import com.spring.shop.service.CategoryService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BookController {
	
	private CategoryService categoryService;
	
	private BookService bookService;
	
	@Autowired
	public BookController(CategoryService categoryService, BookService bookService) {
		this.categoryService = categoryService;
		this.bookService = bookService;
	}
	
	@GetMapping("/admin/goodsEnroll")
	public void goodsEnrollGET(Model model) throws Exception {
		log.info("관리자 - 상품 등록 페이지로 이동");
		
		List<CategoryVO> list = categoryService.getCategoryList();
		
		String categoryList = new ObjectMapper().writeValueAsString(list);
		
		model.addAttribute("categoryList", categoryList);
	}
	
	@GetMapping({"/admin/goodsDetail", "/admin/goodsModify"})
	public void goodsDetailGET(int bookId, PageInfo pageInfo, Model model) throws Exception {
		log.info("관리자 - 상품 상세, 수정 페이지로 이동");
		
		// 카테고리 데이터 
		model.addAttribute("categoryList", new ObjectMapper().writeValueAsString(categoryService.getCategoryList()));
		
		model.addAttribute("PreviousPageInfo", pageInfo);
		
		model.addAttribute("goodsDetail", bookService.goodsDetail(bookId));
	}
	
	@PostMapping("/admin/goodsEnroll")
	public String goodsEnrollPOST(BookVO bookVO, RedirectAttributes redirect) throws Exception {
		log.info("관리자 - 상품 등록");
		
		int result = bookService.goodsEnroll(bookVO);
		
		if(result > 0) {
			redirect.addFlashAttribute("enrollResult", bookVO.getBookName());
			return "redirect:/admin/goodsManage";
		}
		
		return "redirect:/admin/goodsManage";
	}
	
	@PostMapping("/admin/goodsModify")
	public String goodsModifyPOST(BookVO bookVO, RedirectAttributes redirect) throws Exception {
		log.info("관리자 - 상품 정보 수정");
		
		int result = bookService.goodsModify(bookVO);
		
		redirect.addFlashAttribute("modifyResult", result);
		
		return "redirect:/admin/goodsManage";
	}
	
	@PostMapping("/admin/goodsDelete")
	public String goodsDeletePOST(int bookId, RedirectAttributes redirect) throws Exception {
		log.info("관리자 - 상품 정보 삭제");
		
		int result = bookService.goodsDelete(bookId);
		
		redirect.addFlashAttribute("deleteResult", result);
		
		return "redirect:/admin/goodsManage";
	}
}