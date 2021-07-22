package com.spring.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.spring.board.dto.BoardDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardDaoTest {
	
	@Inject
	private BoardDao boardDao;
	
	private BoardDto board;
	
	@Test
	public void List_BoardDto_리턴 () throws Exception {
		
		List<BoardDto> list = boardDao.getBoardList(board);
			
		BoardDto dto1 = list.get(0);
		
		assertThat(dto1.getBoard_content()).isEqualTo("게시글 내용1");
			
	}
	
	@Test
	public void 상세_BoardDto_리턴 () throws Exception {
		board = new BoardDto();
		board.setBoard_seq(1);
		
		BoardDto dto = boardDao.getBoardDetail(board);
		assertThat(dto.getBoard_content()).isEqualTo("게시글 내용1");
	}

}
