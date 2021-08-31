package com.spring.board.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.vo.BoardVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void 게시글_등록_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		
		boardVO.setTitle("서비스테스트1");
        boardVO.setContent("서비스테스트입니다.");
        boardVO.setWriter("서비스테스터1");
        
        boardService.enroll(boardVO);
	}
	
	@Test
	public void 게시글_목록_출력_테스트() throws Exception {
		List<BoardVO> list = boardService.getList();
		
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void 게시글_수정_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(17);
		boardVO.setTitle(null);
        boardVO.setContent("서비스수정테스트입니다.");
        
        int result = boardService.modify(boardVO);
        
        assertThat(0, equalTo(result));
	}
	
	@Test
	public void 게시물_상세_테스트() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(12);
		
		BoardVO detail = boardService.getDetail(vo);
		
		assertNotNull(detail);
	}
	@Test
	public void 게시글_삭제_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(17);
		
		int result = boardService.delete(boardVO);
		
		assertThat(0, equalTo(result));
	}
}
