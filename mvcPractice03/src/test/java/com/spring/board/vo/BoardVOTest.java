package com.spring.board.vo;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BoardVOTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private SimpleDateFormat dateFormat;
	
	@Before
	public void setUp() throws Exception {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}
	
	@Test
	public void BoardVO_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		
		boardVO.setBno(1);
		boardVO.setTitle("테스트1");
		boardVO.setContent("테스트내용1");
		boardVO.setWriter("테스터1");
		boardVO.setRegdate(dateFormat.parse("2021-08-23 11:00:00"));
		
		assertNotNull(boardVO);
		logger.info(boardVO.toString());
	}
}
