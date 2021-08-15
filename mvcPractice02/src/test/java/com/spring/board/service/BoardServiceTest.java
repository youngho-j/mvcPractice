package com.spring.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.common.ResultUtil;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardFileForm;
import com.spring.board.form.BoardForm;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Transactional
public class BoardServiceTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private BoardService boardService;
	
	private BoardForm form;
	
	@Before
	public void setUp() throws Exception {
		form = new BoardForm();
	}
	
	@Test
	public void 주입_테스트() throws Exception {
		logger.info("boardService : {}", boardService);
		assertThat(boardService).isNotNull();
	}
	
	@Test
	public void 목록_출력_테스트() throws Exception {
		form.setFuntion_name("목록 출력");
		form.setCurrent_page_num(1);
		
		ResultUtil resultUtil = boardService.getBoardList(form);
		
		assertThat(resultUtil.getState()).isEqualTo("SUCCESS");
	}
	
	@Test
	public void 상세_출력_테스트() throws Exception {
		form.setBoard_seq(54);
		
		BoardDto result = boardService.getBoardDetail(form);
		
		assertThat(result.getBoard_subject()).isEqualTo("첨부용글");
		assertThat(result.getFiles().size()).isEqualTo(1);
	}
	
	@Test
	@Rollback(true)
	public void 조회수_업데이트_테스트() throws Exception {
		form.setBoard_seq(1);
		form.setSearch_type("S");
		
		BoardDto result = boardService.getBoardDetail(form);
		
		logger.info("result : {}", result);
		
		assertThat(result.getBoard_hits()).isEqualTo(14);
	}
	
	@Test
	@Rollback(true)
	public void 게시글_등록_테스트() throws Exception {
		form.setBoard_writer("테스트1");
		form.setBoard_subject("테스트");
		form.setBoard_content("테스트입니다");
		
		BoardDto dto = boardService.insertBoard(form);
		
		logger.info("dto.result : {}", dto.getResult());
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
	
	@Test
	@Rollback(true)
	public void 게시글_수정_테스트() throws Exception {
		form.setBoard_subject("수정테스트");
		form.setBoard_content("수정테스트입니다");
		form.setBoard_seq(1);
		form.setDelete_file("");
		
		BoardDto dto = boardService.updateBoard(form);
		
		logger.info("dto.result : {}", dto.getResult());
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
	
	@Test
	@Rollback(true)
	public void 게시글_삭제_테스트() throws Exception {
		form.setBoard_seq(1);
		
		BoardDto dto = boardService.deleteBoard(form);
		
		logger.info("dto.result : {}", dto.getResult());
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
	
	@Test
	@Rollback(true)
	public void 답글_작성_테스트() throws Exception {
		form.setBoard_parent_seq(35);
		form.setBoard_writer("답글작성");
		form.setBoard_subject("답글테스트");
		form.setBoard_content("답글 테스트입니다");
		
		BoardDto dto = boardService.insertBoardReply(form);
		
		logger.info("dto.result : {}", dto.getResult());
		
		assertThat(dto.getResult()).isEqualTo("SUCCESS");
	}
	
	@Test
	public void 랜덤_UUID_출력_테스트() throws Exception {
		String test = boardService.getRandomString();
		
		boolean bool = !test.contains("-");
		
		assertThat(bool).isEqualTo(true);
	}
	
	@Test
	public void 첨부파일_정보조회_테스트() throws Exception {
		form.setBoard_parent_seq(35);
		
		MultipartFile MockFile = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello world!".getBytes());
		
		List<MultipartFile> files = new ArrayList<MultipartFile>();
		
		files.add(MockFile);
		
		form.setFiles(files);
		
		List<BoardFileForm> list = boardService.getBoardFileInfo(form);
		
		assertThat(list.size()).isEqualTo(1);
		
	}
}
