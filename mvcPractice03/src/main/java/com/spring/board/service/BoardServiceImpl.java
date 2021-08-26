package com.spring.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.mapper.BoardMapper;
import com.spring.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	// 게시글 등록
	@Override
	public int enroll(BoardVO boardVO) throws Exception {
		return boardMapper.enroll(boardVO);
	}
	
}
