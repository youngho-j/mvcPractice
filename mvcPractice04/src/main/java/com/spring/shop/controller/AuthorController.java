package com.spring.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.shop.service.AuthorService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthorController {
	
	private AuthorService authorService;
	
	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@RequestMapping(value = "/admin/authorEnroll", method = RequestMethod.GET)
	public void authorEnrollGET() throws Exception {
		log.info("작가 등록 페이지로 이동");
	}
	
	@RequestMapping(value = "/admin/authorEnroll", method = RequestMethod.POST)
	public String authorEnrollGET(AuthorVO authorVO, RedirectAttributes redirect) throws Exception {
		int result = authorService.authorEnroll(authorVO);
		if(result != 0) {
			log.info("작가 등록 성공");
			redirect.addFlashAttribute("enroll_result", authorVO.getAuthorName());
			
			return "redirect:/admin/authorManage";
		}
		log.info("작가 등록 실패");
		return "redirect:/admin/authorEnroll";
	}
	
	// URL 배열 처리 - 상세 페이지와 수정 페이지 같은 작가 데이터를 동일하게 사용
	@GetMapping({"/admin/authorDetail", "/admin/authorModify"})
	public void authorGetInfoGET(int authorId, PageInfo pageInfo, Model model) throws Exception {
		log.info("작가 상세or수정 페이지로 이동");
		
		// 상세 페이지 넘어가기전 작기 관리 페이지 정보
		model.addAttribute("PreviousPageInfo", pageInfo);
		
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
	}
	
	@PostMapping("/admin/authorModify")
	public String authorModifyPOST(AuthorVO authorVO, RedirectAttributes redirect) throws Exception {
		log.info("작가 정보 수정");
		
		int result = authorService.authorModify(authorVO);
		
		redirect.addFlashAttribute("modifyResult", result);
		
		return "redirect:/admin/authorManage";
	}
	
	@PostMapping("/admin/authorDelete")
	public String authorDeletePOST(int authorId, RedirectAttributes redirect) throws Exception {
		log.info("작가 정보 삭제 여부 판단");
		
		int result = authorService.authorDelete(authorId);
		
		redirect.addFlashAttribute("deleteResult", result);
		
		return "redirect:/admin/authorManage";
		
	}
}
