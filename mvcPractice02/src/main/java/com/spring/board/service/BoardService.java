package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	
	public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {
		return boardDao.getBoardList(boardForm);
	}

	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
		
		BoardDto boardDto = new BoardDto();
		
		String searchType = boardForm.getSearch_type();
		
		if("S".equals(searchType)) {
			int updateHitCnt = boardDao.updateBoardHits(boardForm);
			
			if(updateHitCnt > 0) {
				boardDto = boardDao.getBoardDetail(boardForm);
			}
			
		} else {
			boardDto = boardDao.getBoardDetail(boardForm);
		}
		
		return boardDto;
	}

	public BoardDto insertBoard(BoardForm form) throws Exception {
		
		BoardDto boardDto = new BoardDto();
		
		int insertCnt = boardDao.insertBoard(form);
		
		if(insertCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");			
		}
		
		return boardDto;
	}
	
	public BoardDto updateBoard(BoardForm boardForm) throws Exception {
		BoardDto boardDto = new BoardDto();
		
		int updateCnt = boardDao.insertBoard(boardForm);
		
		if(updateCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");			
		}
		
		return boardDto;
	}
}
