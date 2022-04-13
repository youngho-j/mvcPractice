package com.spring.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
			redirect.addFlashAttribute("enroll_result", authorVO.getAuthorName());
			return "redirect:/admin/authorManage";
		}
		
		redirect.addFlashAttribute("enroll_result", "작가 등록에 실패하였습니다.");
		return "redirect:/admin/authorEnroll";
	}
	
	@GetMapping("/admin/authorDetail")
	public String authorDetailGET(int authorId, PageInfo pageInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		log.info("작가 상세 페이지로 이동");
		
		AuthorVO authorInfo = authorService.authorGetDetail(authorId);
		
		if(ObjectUtils.isEmpty(authorInfo)) {
			
			redirectAttributes.addFlashAttribute("alertMsg", "존재하지 않는 작가 ID입니다.");
			
			return "redirect:/admin/authorManage";
		}
		
		// 상세 페이지 넘어가기전 작기 관리 페이지 정보
		model.addAttribute("PreviousPageInfo", pageInfo);
		
		model.addAttribute("authorInfo", authorInfo);
		
		return "/admin/authorDetail";
	}
	
	@GetMapping("/admin/authorModify")
	public String authorModifyGET(int authorId, PageInfo pageInfo, Model model, RedirectAttributes redirectAttributes) throws Exception {
		log.info("작가 수정 페이지로 이동");
		
		AuthorVO authorInfo = authorService.authorGetDetail(authorId);
		
		if(ObjectUtils.isEmpty(authorInfo)) {
			
			redirectAttributes.addFlashAttribute("alertMsg", "존재하지 않는 작가 ID입니다.");
			
			return "redirect:/admin/authorManage";
		}
		
		// 상세 페이지 넘어가기전 작기 관리 페이지 정보
		model.addAttribute("PreviousPageInfo", pageInfo);
		
		model.addAttribute("authorInfo", authorInfo);
		
		return "/admin/authorModify";
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
