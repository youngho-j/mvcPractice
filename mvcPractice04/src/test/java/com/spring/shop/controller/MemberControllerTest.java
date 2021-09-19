package com.spring.shop.controller;

import static org.hamcrest.Matcher.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
public class MemberControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void 로그인페이지_호출_테스트() throws Exception {
		mock.perform(get("/member/login"))
		.andExpect(status().isOk())
		.andExpect(view().name("member/login"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입페이지_호출_테스트() throws Exception {
		mock.perform(get("/member/join"))
		.andExpect(status().isOk())
		.andExpect(view().name("member/join"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입후_메인페이지_호출() throws Exception {
		mock.perform(post("/member/join")
				.param("memberId", "test5")
				.param("memberPw", "test5")
				.param("memberName", "test5")
				.param("memberMail", "test5")
				.param("memberAddr1", "test5")
				.param("memberAddr2", "test5")
				.param("memberAddr3", "test5")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/main"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입시_아이디중복체크_테스트() throws Exception {
		mock.perform(post("/member/memberIdChk")
				.param("memberId", "test3"))
		.andExpect(status().isOk())
		.andExpect(content().string("fail"))
		.andDo(print());
	}
}
