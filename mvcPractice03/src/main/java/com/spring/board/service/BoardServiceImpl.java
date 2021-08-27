package com.spring.board.service;

import java.util.List;

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
		
		if(boardVO.getTitle() == null || boardVO.getContent() == null || boardVO.getWriter() == null) {
			return 0;
		}
		
		return boardMapper.enroll(boardVO);
	}

	@Override
	public List<BoardVO> getList() throws Exception {
		return boardMapper.getList();
	}
	
}
