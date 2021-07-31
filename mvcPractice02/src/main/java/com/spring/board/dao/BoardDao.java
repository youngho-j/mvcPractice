package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String NAMESPACE = "com.spring.board.boardMapper";
	
	// 글 갯수
	public int getBoardCnt(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardCnt", boardForm);
	}
	
	// 목록 데이터
	public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".getBoardList");
	}
	
	// 상세 데이터
	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardDetail", boardForm);
	}
	
	// 조회수 수정
	public int updateBoardHits(BoardForm boardForm) throws Exception {
		return sqlSession.update(NAMESPACE + ".updateBoardHits", boardForm);
	}
	
	// 글 등록
	public int insertBoard(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoard", boardForm);
	}

	// 글 수정
	public int updateBoard(BoardForm boardForm) throws Exception {
		return sqlSession.update(NAMESPACE + ".updateBoard", boardForm);
	}

	// 글 삭제
	public int deleteBoard(BoardForm boardForm) {
		return sqlSession.delete(NAMESPACE + ".deleteBoard", boardForm);
	}
	
	
}
