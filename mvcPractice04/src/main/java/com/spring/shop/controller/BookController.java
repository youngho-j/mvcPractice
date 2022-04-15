package com.spring.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.shop.service.BookService;
import com.spring.shop.service.CategoryService;
import com.spring.shop.util.FileManager;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;
import com.spring.shop.vo.ImageInfoVO;

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
	
	@ModelAttribute("categoryList")
	private String getCategoryList() throws Exception {
		List<CategoryVO> list = categoryService.getCategoryList();
		return new ObjectMapper().writeValueAsString(list);
	}
	
	@GetMapping("/admin/goodsEnroll")
	public void goodsEnrollGET(Model model) throws Exception {
		log.info("관리자 - 상품 등록 페이지로 이동");
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
	
	@GetMapping("/admin/goodsModify")
	public String goodsModifyGET(int bookId, 
			@ModelAttribute("PreviousPageInfo") PageInfo pageInfo,
			Model model,
			RedirectAttributes redirect) throws Exception {
		log.info("관리자 - 상품 수정 페이지로 이동");
		BookVO bookInfo = bookService.goodsDetail(bookId);
		
		if(ObjectUtils.isEmpty(bookInfo)) {
			redirect.addFlashAttribute("pageInfo", pageInfo);
			return "redirect:/admin/goodsManage";
		}
		
		model.addAttribute("goodsDetail", bookInfo);
		
		return "/admin/goodsModify";
	}
	
	@GetMapping("/admin/goodsDetail")
	public String goodsDetailGET(int bookId, 
			@ModelAttribute("PreviousPageInfo") PageInfo pageInfo,
			Model model,
			RedirectAttributes redirect) throws Exception {
		log.info("관리자 - 상품 상세 페이지로 이동");
		BookVO bookInfo = bookService.goodsDetail(bookId);
		
		if(ObjectUtils.isEmpty(bookInfo)) {
			redirect.addFlashAttribute("pageInfo", pageInfo);
			return "redirect:/admin/goodsManage";
		}
		
		model.addAttribute("goodsDetail", bookInfo);
		
		return "/admin/goodsDetail";
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
		
		List<ImageInfoVO> list = bookService.goodsDelete(bookId);
		
		if(!CollectionUtils.isEmpty(list)) {
			
			for(ImageInfoVO image : list) {
				FileManager file = new FileManager
						.Builder(image.getUploadPath())
						.fileName(image.getUuid() + "_" + image.getFileName()).build();
				
				file.deleteImageFile();
			}
			
			redirect.addFlashAttribute("deleteResult", list.size());
			return "redirect:/admin/goodsManage";
		}
		
		log.info("삭제할 이미지 정보가 존재하지 않습니다.");
		return "redirect:/admin/goodsManage";
	}
}