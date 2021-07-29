package com.spring.board.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@Transactional
public class BoardServiceTest {
	
	@Inject
	private BoardService boardService;
	
	private BoardForm form;
	
	@Test
	public void 주입_테스트() throws Exception {
		log.info(boardService);
		assertNotNull(boardService);
	}
	
	@Test
	public void 목록_출력_테스트() throws Exception {
		boardService.getBoardList(form).forEach(board ->log.info(board));
	}
	
	@Test
	public void 상세_출력_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_seq(1);
		
		BoardDto result = boardService.getBoardDetail(form);
		
		log.info(result);
		
		assertThat(result.getBoard_subject()).isEqualTo("게시글 제목1");
	}
	
	@Test
	@Rollback(true)
	public void 조회수_업데이트_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_seq(1);
		form.setSearch_type("S");
		
		BoardDto result = boardService.getBoardDetail(form);
		
		log.info(result);
		
		assertThat(result.getBoard_hits()).isEqualTo(14);
	}
	
	@Test
	@Rollback(true)
	public void 게시글_등록_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_writer("테스트1");
		form.setBoard_subject("테스트");
		form.setBoard_content("테스트입니다");
		
		BoardDto dto = boardService.insertBoard(form);
		
		log.info(dto);
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
	
	@Test
	@Rollback(true)
	public void 게시글_수정_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_subject("수정테스트");
		form.setBoard_content("수정테스트입니다");
		form.setBoard_seq(1);
		
		BoardDto dto = boardService.updateBoard(form);
		
		log.info(dto);
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
	
	@Test
	@Rollback(true)
	public void 게시글_삭제_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_seq(1);
		
		BoardDto dto = boardService.deleteBoard(form);
		
		log.info(dto);
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
}
