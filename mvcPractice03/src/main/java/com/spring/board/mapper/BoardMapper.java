package com.spring.board.mapper;

import com.spring.board.vo.BoardVO;

public interface BoardMapper {
	
	// 게시글 등록 
	public int enroll(BoardVO boardVO) throws Exception;
}
