package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.dto.BoardDto;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	
	public List<BoardDto> getBoardList(BoardDto boardDto) throws Exception {
		return boardDao.getBoardList(boardDto);
	}

	public BoardDto getBoardDetail(BoardDto boardForm) throws Exception {
		return boardDao.getBoardDetail(boardForm);
	}
}
