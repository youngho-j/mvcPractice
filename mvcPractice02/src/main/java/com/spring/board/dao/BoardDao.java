package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDto;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.spring.board.boardMapper";
	
	// 목록 데이터
	public List<BoardDto> getBoardList(BoardDto boardDto) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getBoardList");
	}
	
	// 상세 데이터
	public BoardDto getBoardDetail(BoardDto boardDto) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardDetail", boardDto);
	}
}
