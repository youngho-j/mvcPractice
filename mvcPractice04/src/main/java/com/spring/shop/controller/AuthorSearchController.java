package com.spring.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.shop.service.AuthorSearchService;
import com.spring.shop.util.PageInfo;
import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthorSearchController {
	
	private AuthorSearchService authorSearchService;
	
	@Autowired
	public AuthorSearchController(AuthorSearchService authorSearchService) {
		this.authorSearchService = authorSearchService;
	}
	
	@RequestMapping(value = "/admin/authorManage", method = RequestMethod.GET)
	public void authorManageGET(@ModelAttribute("PreviousPageInfo") PageInfo paging, Model model) throws Exception {
		log.info("작가 관리 페이지로 이동");
		
		// 작가 목록 데이터
		List<AuthorVO> authorList = authorSearchService.authorGetList(paging);
		
		// 작가 존재 여부 체크
		if(CollectionUtils.isEmpty(authorList)) {
			model.addAttribute("listData", "empty");
		} else {
			model.addAttribute("listData", authorList);			
		}		
		model.addAttribute("pagingManager", new PagingManager(paging, authorSearchService.authorGetTotal(paging)));
	}
	
	@GetMapping("/admin/authorSearch")
	public void authorSearchGET(@ModelAttribute("PreviousPageInfo") PageInfo pageInfo, Model model) throws Exception {
		log.info("작가 등록 과정 중 작가 검색 팝업창 실행");
		// 팝업창에 맞도록 작가 5명씩 출력
		pageInfo.setViewPerPage(5);

		List<AuthorVO> authorList = authorSearchService.authorGetList(pageInfo);
		
		if(CollectionUtils.isEmpty(authorList)) {
			model.addAttribute("listData", "empty");
		} else {
			model.addAttribute("listData", authorList);
		}
		
		// 페이징 관련 정보	
		model.addAttribute("pagingManager", new PagingManager(pageInfo, authorSearchService.authorGetTotal(pageInfo)));
	}
}
