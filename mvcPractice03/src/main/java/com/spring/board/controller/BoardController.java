package com.spring.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.service.BoardService;
import com.spring.board.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/list")
	public void boardListGET(Model model) throws Exception {
		logger.info("게시판 목록 페이지");
		model.addAttribute("list", boardService.getList());
	}
	
	@GetMapping("/enroll")
	public void boardEnrollGET() throws Exception { 
		logger.info("게시글 등록 페이지");		
	}
	
	@PostMapping("/enroll")
	public String boardEnrollPOST(BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("게시글 입력 정보" + boardVO);
		
		int resultSQL = boardService.enroll(boardVO);
		
		resultCheck(resultSQL, redirectAttributes);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/detail")
	public void boardDetailGET(Model model, BoardVO boardVO) throws Exception {
		logger.info("게시판 상세 페이지");
		model.addAttribute("detail", boardService.getDetail(boardVO));
	}
	
	@GetMapping("/delete")
	public String boardDeleteGET(BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("게시글 삭제");
		
		int resultSQL = boardService.delete(boardVO);
		
		resultCheck(resultSQL, redirectAttributes);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/modify")
	public void boardUpdateGET(Model model, BoardVO boardVO) throws Exception {
		logger.info("게시글 수정 페이지");
		model.addAttribute("before", boardService.getDetail(boardVO));
	}
	
	@PostMapping("/modify")
	public String boardUpdatePOST(BoardVO boardVO, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("게시글 수정 정보" + boardVO);
		
		int resultSQL = boardService.modify(boardVO);
		
		resultCheck(resultSQL, redirectAttributes);
		
		return "redirect:/board/list";
	}
	
	private RedirectAttributes resultCheck(int resultSQL, RedirectAttributes redirectAttributes) {
		if(resultSQL == 1) {
			return redirectAttributes.addFlashAttribute("result", "success");
		}
		return redirectAttributes.addFlashAttribute("result", "");
	}
	
}
