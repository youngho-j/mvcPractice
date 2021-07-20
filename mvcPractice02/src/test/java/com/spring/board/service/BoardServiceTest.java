package com.spring.board.service;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.board.dto.BoardDto;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardServiceTest {
	
	@Inject
	private BoardService boardService;
	
	private BoardDto dto;
	
	@Test
	public void 주입_테스트() throws Exception {
		log.info(boardService);
		assertNotNull(boardService);
	}
	
	@Test
	public void 목록_출력_테스트() throws Exception {
		boardService.getBoardList(dto).forEach(board ->log.info(board));
	}
}
