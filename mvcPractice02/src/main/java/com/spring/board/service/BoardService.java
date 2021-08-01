package com.spring.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.common.CommonDto;
import com.spring.board.common.CommonForm;
import com.spring.board.common.PagingUtil;
import com.spring.board.common.ResultUtil;
import com.spring.board.dao.BoardDao;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public ResultUtil getBoardList(BoardForm boardForm) throws Exception {
		
		ResultUtil resultUtil = new ResultUtil();
		
		CommonDto commonDto = new CommonDto();
		
		int totalCount = boardDao.getBoardCnt(boardForm);
		
		if(totalCount != 0) {
			CommonForm commonForm = new CommonForm();
			
			commonForm.setFuntion_name(boardForm.getFuntion_name());
			commonForm.setCurrent_page_num(boardForm.getCurrent_page_num());
			commonForm.setCount_per_page(10);
			commonForm.setCount_per_list(10);
			commonForm.setTotal_list_count(totalCount);
			
			commonDto = PagingUtil.setPageUtil(commonForm);
		}
		
		boardForm.setLimit(commonDto.getLimit());
		boardForm.setOffset(commonDto.getOffset());
		
		List<BoardDto> list = boardDao.getBoardList(boardForm);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pagination", commonDto.getPagination());
		
		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");
		
		return resultUtil;
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
		
		int updateCnt = boardDao.updateBoard(boardForm);
		
		if(updateCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");			
		}
		
		return boardDto;
	}
	
	public BoardDto deleteBoard(BoardForm boardForm) throws Exception {
		BoardDto boardDto = new BoardDto();
		
		int deleteCnt = boardDao.deleteBoard(boardForm);
		
		if(deleteCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");
		}
		return boardDto;
	}
}
