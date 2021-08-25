package com.spring.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/list")
	public void boardListGET() throws Exception {
		logger.info("게시판 목록 페이지");
	}
	
	@GetMapping("/enroll")
	public void boardEnrollGET() throws Exception { 
		logger.info("게시글 등록 페이지");		
	}
	
	@PostMapping("/enroll")
	public String boardEnrollPOST(BoardVO boardVO) throws Exception {
		logger.info("게시글 입력 정보" + boardVO);
		
		boardService.enroll(boardVO);
		
		return "redirect:/board/list";
	}
}
