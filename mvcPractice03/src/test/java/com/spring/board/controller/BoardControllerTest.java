package com.spring.board.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class BoardControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void 목록페이지_이동과_목록값_확인() throws Exception {
		mock.perform(get(("/board/list")))
		.andExpect(status().isOk())
		.andExpect(view().name("board/list"))
		.andExpect(model().attributeExists("list"))
		.andDo(print());
	}
	
	@Test
	public void 등록페이지_이동() throws Exception {
		mock.perform(get(("/board/enroll")))
		.andExpect(status().isOk())
		.andExpect(view().name("board/enroll"))
		.andDo(print());
	}
	
	@Test
	public void 등록후_리스트페이지_이동() throws Exception {
		mock.perform(post(("/board/enroll")))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/board/list"))
		.andExpect(flash().attribute("result", ""))
		.andDo(print());
	}
	
	@Test
	public void 상세페이지_이동() throws Exception {
		mock.perform(get("/board/detail")
				.param("bno", "6"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/detail"))
		.andDo(print());
		
	}
	
	@Test
	public void 삭제후_리스트페이지_이동() throws Exception {
		mock.perform(get("/board/delete")
				.param("bno", "18"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/board/list"))
		.andExpect(flash().attribute("result", ""))
		.andDo(print());
	}
	
	@Test
	public void 수정페이지_이동() throws Exception {
		mock.perform(get("/board/modify")
				.param("bno", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("board/modify"))
		.andDo(print());
		
	}
	
	@Test
	public void 수정후_리스트페이지_이동() throws Exception {
		mock.perform(post("/board/modify")
				.param("bno", "33"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/board/list"))
		.andExpect(flash().attribute("result", ""))
		.andDo(print());
	}
}
