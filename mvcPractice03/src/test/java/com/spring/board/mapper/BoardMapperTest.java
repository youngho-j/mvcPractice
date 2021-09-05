package com.spring.board.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.model.PagingModel;
import com.spring.board.vo.BoardVO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void 게시글_등록_쿼리_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		
		boardVO.setTitle("쿼리 테스트1");
		boardVO.setContent("쿼리테스트 내용1");
		boardVO.setWriter("쿼리 작성자1");
		
		boardMapper.enroll(boardVO);
		
	}
	
	@Test
	public void 게시글_목록_출력_쿼리_테스트() throws Exception {
		PagingModel pagingModel = new PagingModel();
		
		List<BoardVO> list = boardMapper.getList(pagingModel);
		
		assertNotNull(list.get(0));
	}
	
	@Test
	public void 게시물_수정_쿼리_테스트() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(11);
		vo.setTitle("수정1");
		vo.setContent("수정했수");
		
		int result = boardMapper.modify(vo);
		assertThat(1, equalTo(result));
	}
	
	@Test
	public void 게시물_상세출력_쿼리_테스트() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setBno(11);
		BoardVO detail = boardMapper.getDetail(vo);
		
		assertNotNull(detail);
		log.info(detail);
	}
	
	@Test
	public void 게시물_삭제_쿼리_테스트() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(17);
		
		assertThat(1, equalTo(boardMapper.delete(boardVO)));
	}
	
	@Test
	public void 총_게시글_수() throws Exception {
		assertThat(11, equalTo(boardMapper.getTotalCount()));		
	}
}
