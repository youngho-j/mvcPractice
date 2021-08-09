package com.spring.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardFileForm;
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
		return sqlSession.selectList(NAMESPACE + ".getBoardList", boardForm);
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
	public int deleteBoard(BoardForm boardForm) throws Exception {
		return sqlSession.delete(NAMESPACE + ".deleteBoard", boardForm);
	}
	
	// 글 그룹번호 조회
	public int getBoardReRef(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardReRef", boardForm);
	}
	
	// 답글 등록시 부모 게시글 정보 조회
	public BoardDto getBoardReplyInfo(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardReplyInfo", boardForm);
	}
	
	// 답글 등록시 기존에 등록된 답글 순서 수정(증가)
	public int updateBoardReSeq(BoardForm boardForm) throws Exception {
		return sqlSession.update(NAMESPACE + ".updateBoardReSeq", boardForm);
	}
	
	// 답글 등록
	public int insertBoardReply(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardReply", boardForm);
	}
	
	// 첨부 파일 등록(글 등록 후 실행되어야함)
	public int insertBoardFile(BoardFileForm boardFileForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardFile", boardFileForm);
	}
	
	// 게시글에 첨부된 파일 목록 조회
	public List<BoardFileForm> getBoardFileList(BoardFileForm boardFileForm) {
		return sqlSession.selectList(NAMESPACE + ".getBoardFileList", boardFileForm);
	}
}
