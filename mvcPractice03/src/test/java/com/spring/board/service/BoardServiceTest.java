package com.spring.board.service;

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
}
