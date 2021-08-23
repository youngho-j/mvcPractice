package com.spring.board.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.vo.BoardVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void 게시글_등록_쿼리문_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		
		boardVO.setTitle("쿼리 테스트1");
		boardVO.setContent("쿼리테스트 내용1");
		boardVO.setWriter("쿼리 작성자1");
		
		boardMapper.enroll(boardVO);
	}
}
