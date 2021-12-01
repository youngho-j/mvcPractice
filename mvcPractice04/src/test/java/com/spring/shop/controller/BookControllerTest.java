package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class BookControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void 메인페이지_호출_테스트() throws Exception {
		mock.perform(get("/main"))
		.andExpect(status().isOk())
		.andExpect(view().name("main"))
		.andDo(print());
	}
	
	@Test
	public void 이미지_출력_테스트() throws Exception {
		mock.perform(get("/display")
				.param("fileName", "디아템.jpg"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.IMAGE_JPEG))
		.andDo(print());
	}
	
	@Test
	public void 이미지_정보_JSON_리턴_테스트() throws Exception {
		mock.perform(get("/getImageInfo")
				.param("bookId", "16"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print());
	}
	
	@Test
	public void 상품_검색_정보_리턴_테스트() throws Exception {
		mock.perform(get("/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("goodsList"))		
		.andExpect(view().name("search"))
		.andDo(print());
	}
}
