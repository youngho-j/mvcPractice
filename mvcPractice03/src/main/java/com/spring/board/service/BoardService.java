package com.spring.board.service;

import com.spring.board.vo.BoardVO;

public interface BoardService {
	
	// 게시글 등록
	public int enroll(BoardVO boardVO) throws Exception;
	
}
