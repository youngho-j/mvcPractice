package com.spring.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.shop.service.AuthorService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class AdminController {
	
	@Autowired
	private AuthorService authorService;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public void adminMainGET() throws Exception {
		log.info("관리자 페이지로 이동");
	}
	
	@RequestMapping(value = "/goodsEnroll", method = RequestMethod.GET)
	public void goodsEnrollGET() throws Exception {
		log.info("상품 등록 페이지로 이동");
	}
	
	@RequestMapping(value = "/goodsManage", method = RequestMethod.GET)
	public void goodsManageGET() throws Exception {
		log.info("상품 관리 페이지로 이동");
	}
	
	@RequestMapping(value = "/authorEnroll", method = RequestMethod.GET)
	public void authorEnrollGET() throws Exception {
		log.info("작가 등록 페이지로 이동");
	}
	
	@RequestMapping(value = "/authorEnroll", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/authorManage", method = RequestMethod.GET)
	public void authorManageGET(PageInfo paging, Model model) throws Exception {
		log.info("작가 관리 페이지로 이동");
		
		// 작가 목록 데이터
		List<AuthorVO> list = authorService.authorGetList(paging);
		
		// 작가 존재 여부 체크
		if(!list.isEmpty()) {
			model.addAttribute("list", list);			
		} else {
			model.addAttribute("checkResult", "empty");
		}
		
		// 페이징 관련 정보	
		model.addAttribute("pagingManager", new PagingManager(paging, authorService.authorGetTotal(paging)));
	}
	
	@RequestMapping(value = "/authorDetail", method = RequestMethod.GET)
	public void authorGetInfoGET(int authorId, PageInfo pageInfo, Model model) throws Exception {
		log.info("작가 상세 페이지로 이동");
		
		// 상세 페이지 넘어가기전 작기 관리 페이지 정보
		model.addAttribute("pageInfo", pageInfo);
		
		model.addAttribute("authorInfo", authorService.authorGetDetail(authorId));
	}
}
