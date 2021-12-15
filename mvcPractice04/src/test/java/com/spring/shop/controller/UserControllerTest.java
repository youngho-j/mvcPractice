package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

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
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class UserControllerTest {
	
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
		.andExpect(model().attributeExists("domestic"))
		.andExpect(model().attributeExists("international"))
		.andExpect(view().name("/user/main"))
		.andDo(print());
	}
	
	@Test
	public void 로그인페이지_호출_테스트() throws Exception {
		mock.perform(get("/goLogin"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/goLogin"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입페이지_호출_테스트() throws Exception {
		mock.perform(get("/join"))
		.andExpect(status().isOk())
		.andExpect(view().name("user/join"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입후_메인페이지_호출() throws Exception {
		mock.perform(post("/join")
				.param("memberId", "test6")
				.param("memberPw", "test5")
				.param("memberName", "test5")
				.param("memberMail", "test5")
				.param("memberAddr1", "test5")
				.param("memberAddr2", "test5")
				.param("memberAddr3", "test5")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("user/main"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입시_아이디중복체크_테스트() throws Exception {
		mock.perform(post("/memberIdChk")
				.param("memberId", "test6"))
		.andExpect(status().isOk())
		.andExpect(content().string("fail"))
		.andDo(print());
	}
	
	@Test
	public void 회원가입시_인증메일_테스트() throws Exception {
		mock.perform(get("/mailCheck")
				.param("email","test45"))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void 로그인_테스트() throws Exception {
		mock.perform(post("/login")
				.param("memberId", "admin")
				.param("memberPw", "test"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/goLogin"))
		.andDo(print());
	}
	
	@Test
	public void GET방식_로그아웃_테스트() throws Exception {
		mock.perform(get("/logout"))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/main"))
		.andDo(print());
	}
	
	@Test
	public void 비동기_로그아웃_테스트() throws Exception {
		mock.perform(post("/logout"))
		.andExpect(status().isOk())
		.andExpect(request().sessionAttributeDoesNotExist("member"))
		.andDo(print());
	}
	
	@Test
	public void 상품검색_페이지이동_테스트() throws Exception {
		mock.perform(get("/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("goodsListResult"))		
		.andExpect(view().name("/user/search"))
		.andDo(print());
	}
	
	@Test
	public void 상품검색_검색타입_키워드_입력_페이지이동_테스트() throws Exception {
		mock.perform(get("/search")
				.param("type", "T")
				.param("keyword", ""))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("goodsListResult"))		
		.andExpect(view().name("/user/search"))
		.andDo(print());
	}
}
