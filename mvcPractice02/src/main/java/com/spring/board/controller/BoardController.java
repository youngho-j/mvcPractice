package com.spring.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 게시판 - 목록 페이지 이동 */
	@RequestMapping(value = "/boardList")
	public String boardList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "board/boardList";
	}
	
	/* 게시판 - 목록 데이터 조회 */
	@RequestMapping(value = "/getBoardList")
	@ResponseBody
	public List<BoardDto> getBoardList(HttpServletRequest req, HttpServletResponse res, BoardForm boardform) throws Exception {
		List<BoardDto> boardDtoList = boardService.getBoardList(boardform);
		return boardDtoList;
	}
	
	/* 게시판 - 상세 페이지 이동 */
	@RequestMapping(value = "/boardDetail")
	public String boardDetail(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "board/boardDetail";
	}
	
	/* 게시판 - 상세 데이터 조회 */
	@RequestMapping(value = "/getBoardDetail")
	@ResponseBody
	public BoardDto getBoardDetail(HttpServletRequest req, HttpServletResponse res, BoardForm boardForm) throws Exception {
		BoardDto boardDto = boardService.getBoardDetail(boardForm);
		return boardDto;
	}
}
