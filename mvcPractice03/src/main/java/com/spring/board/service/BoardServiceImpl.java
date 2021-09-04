package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.mapper.BoardMapper;
import com.spring.board.model.PagingModel;
import com.spring.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	
	// 게시글 등록
	@Override
	public int enroll(BoardVO boardVO) throws Exception {
		
		if(enrollNullCheck(boardVO)) {
			return 0;
		}
		
		return boardMapper.enroll(boardVO);
	}

	@Override
	public List<BoardVO> getList(PagingModel pagingModel) throws Exception {
		
		return boardMapper.getList(pagingModel);
	}

	@Override
	public int modify(BoardVO boardVO) throws Exception {
		
		if(modifyNullCheck(boardVO)) {
			return 0;
		}
		
		return boardMapper.modify(boardVO);
	}
	
	
	@Override
	public BoardVO getDetail(BoardVO boardVO) throws Exception {
		return boardMapper.getDetail(boardVO);
	}
	
	@Override
	public int delete(BoardVO boardVO) throws Exception {
		return boardMapper.delete(boardVO);
	}
	
	// 등록시 데이터 값 Null인지 체크
	private boolean enrollNullCheck(BoardVO boardVO) throws Exception {
		
		if(boardVO.getTitle() == null || boardVO.getContent() == null || boardVO.getWriter() == null) {
			return true;
		}
		
		return false;
	}
	
	// 수정시 데이터 값 Null인지 체크
	private boolean modifyNullCheck(BoardVO boardVO) throws Exception {
	
		if(boardVO.getTitle() == null || boardVO.getContent() == null) {
			return true;
		}
	
		return false;
	}

}
