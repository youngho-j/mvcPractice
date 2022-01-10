package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@WithMockUser(username = "testUser", roles = {"ADMIN"})
public class AuthorSearchControllerTest2 {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders
				.webAppContextSetup(wac)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}
	
	@Test
	public void 관리자_작가관리_페이지_호출_테스트() throws Exception {
		mock.perform(get("/admin/authorManage"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("list"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 작가목록_팝업창_호출_테스트() throws Exception {
		// 상품 등록 페이지에서 실행됨
		mock.perform(get("/admin/authorSearch"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("list"))
		.andExpect(view().name("admin/authorSearch"))
		.andDo(print());
	}
}
