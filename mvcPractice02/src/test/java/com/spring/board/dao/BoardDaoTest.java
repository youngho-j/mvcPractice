package com.spring.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
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
	
	@Inject
	private BoardDao boardDao;
	
	private BoardForm form;
	
	@Test
	public void List_BoardDto_리턴() throws Exception {
		
		List<BoardDto> list = boardDao.getBoardList(form);
			
		BoardDto dto1 = list.get(0);
		
		// mapper에서 출력시 역순정렬을 사용했으므로 변경됨
		assertThat(dto1.getBoard_content()).isEqualTo("게시글 내용5");
			
	}
	
	@Test
	public void 상세_BoardDto_리턴() throws Exception {
		form = new BoardForm();
		form.setBoard_seq(1);
		
		BoardDto dto = boardDao.getBoardDetail(form);
		assertThat(dto.getBoard_content()).isEqualTo("게시글 내용1");
	}

}
