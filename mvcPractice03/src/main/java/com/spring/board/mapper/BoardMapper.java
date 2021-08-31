package com.spring.board.mapper;

import java.util.List;

import com.spring.board.vo.BoardVO;

public interface BoardMapper {
	
	// 게시글 등록 
	public int enroll(BoardVO boardVO) throws Exception;
	
	// 게시글 목록 출력
	public List<BoardVO> getList() throws Exception;
	
	// 게시글 수정
	public int modify(BoardVO boardVO) throws Exception;
	
	// 게시글 상세 출력
	public BoardVO getDetail(BoardVO boardVO) throws Exception;
	
	// 게시글 삭제
	public int delete(BoardVO boardVO) throws Exception;
}
