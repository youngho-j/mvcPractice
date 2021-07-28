package com.spring.board.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.board.form.BoardForm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Transactional
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void 리스트_페이지_출력_확인_테스트() throws Exception {
		mock.perform(get("/board/boardList"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/boardList"))
		.andDo(print());
	}
	
	@Test
	public void 리스트_데이터_확인_테스트() throws Exception {
		mock.perform(get("/board/getBoardList").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.[1].board_writer").value("게시글 작성자1"))
		.andDo(print());
	}
	
	@Test
	public void 상세_페이지_출력_확인_테스트() throws Exception {
		mock.perform(get("/board/boardDetail"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/boardDetail"));
	}
	
	@Test
	public void 상세_데이터_확인_테스트() throws Exception {
		mock.perform(get("/board/getBoardDetail").param("board_seq", "1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("board_writer").value("게시글 작성자1"))
		.andDo(print());
	}
	
	@Test
	public void 등록_페이지_출력_확인_테스트() throws Exception {
		mock.perform(get("/board/boardWrite"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/boardWrite"));
	}
	
	@Test
	@Rollback(true)
	public void 게시글_등록_확인_테스트() throws Exception {
		BoardForm form = new BoardForm();
		
		form.setBoard_subject("test");
		form.setBoard_writer("tester");
		form.setBoard_content("testing");
		
		mock.perform(post("/board/insertBoard")
		.content(objectMapper.writeValueAsString(form))
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("result").value("SUCCESS"))
		.andDo(print());
	}
	
	@Test
	public void 수정_페이지_출력_확인_테스트() throws Exception {
		mock.perform(get("/board/boardUpdate"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/boardUpdate"));
	}
	
	@Test
	@Rollback(true)
	public void 게시글_수정_확인_테스트() throws Exception {
		BoardForm form = new BoardForm();
		
		form.setBoard_subject("test");
		form.setBoard_content("testing");
		
		mock.perform(post("/board/updateBoard")
				.content(objectMapper.writeValueAsString(form))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("result").value("SUCCESS"))
		.andDo(print());
	}
}
