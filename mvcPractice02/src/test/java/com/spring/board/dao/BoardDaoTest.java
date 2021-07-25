package com.spring.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	
	@Inject
	private BoardDao boardDao;
	
	private BoardForm form;
	
	@Test
	public void List_BoardDto_리턴() throws Exception {
		
		List<BoardDto> list = boardDao.getBoardList(form);
		
		logger.info("List 조회");
		logger.info("===================================");
		
		if(list.size() > 0) {
			for(BoardDto dto : list) {
				logger.info("번호 : " + dto.getBoard_seq());
				logger.info("제목 : " + dto.getBoard_subject());
				logger.info("조회수 : " + dto.getBoard_hits());
				logger.info("==================================");
			}
			BoardDto dto1 = list.get(0);
			assertThat(dto1.getBoard_content()).isEqualTo("게시글 내용5");
		} else {
			logger.info("데이터 없음");
		}
	}
	
	@Test
	public void 상세_BoardDto_리턴() throws Exception {
		form = new BoardForm();
		form.setBoard_seq(1);
		
		BoardDto dto = boardDao.getBoardDetail(form);
		
		logger.info("Dto 조회");
		logger.info("===================================");
		
		if(!dto.equals(null)) {
			logger.info("번호 : " + dto.getBoard_seq());
			logger.info("제목 : " + dto.getBoard_subject());
			logger.info("조회수 : " + dto.getBoard_hits());
			logger.info("==================================");
			assertThat(dto.getBoard_content()).isEqualTo("게시글 내용1");
		} else {
			logger.info("데이터 없음");
		}
	}
	
	@Test
	public void 조회수_증가_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_seq(1);
		
		logger.info("조회수 업데이트");
		logger.info("===================================");
		
		int hits = boardDao.updateBoardHits(form);
		
		if(hits == 1) {
			logger.info("조회수 업데이트 성공 " + hits);
			assertThat(hits).isEqualTo(1);
		} else {
			logger.info("조회수 업데이트 실패");			
		}
	}
	
	@Test
	public void 게시글_등록_테스트() throws Exception {
		form = new BoardForm();
		form.setBoard_writer("테스트1");
		form.setBoard_subject("테스트");
		form.setBoard_content("테스트입니다");
		
		int result = boardDao.insertBoard(form);
		
		if(result == 1) {
			logger.info("게시글 등록 성공 " + result);
			assertThat(result).isEqualTo(1);
		} else {
			logger.info("게시글 등록 실패");			
		}
	}

}
