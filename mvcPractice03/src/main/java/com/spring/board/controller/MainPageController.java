package com.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.board.dto.PageDataDTO;
import com.spring.board.model.PagingModel;
import com.spring.board.service.BoardService;

@Controller
public class MainPageController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goMainPage(Model model, PagingModel pagingModel) throws Exception {
		model.addAttribute("list", boardService.getList(pagingModel));
		
		PageDataDTO pageData = new PageDataDTO(pagingModel, boardService.getTotalPost(pagingModel));
		
		model.addAttribute("pageData", pageData);
		
		return "/board/list";
	}
}
