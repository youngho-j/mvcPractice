package com.spring.shop.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
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

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@WithMockUser(username = "testUser", roles = {"ADMIN"})
public class AuthorSearchControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private MockMvc mock;
	
	private static AuthorVO localAuthorInfo;
	private static AuthorVO foreignAuthorInfo;
	
	@BeforeClass
	public static void setUp() {
		localAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("김하영");
		localAuthorInfo.setAuthorProfile("국내작가");
		
		foreignAuthorInfo = new AuthorVO();
		
		foreignAuthorInfo.setNationId("02");
		foreignAuthorInfo.setAuthorName("Sam");
		foreignAuthorInfo.setAuthorProfile("외국작가");
	}
	
	@Before
	public void beforeMethod() {
		this.mock = MockMvcBuilders
				.webAppContextSetup(wac)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		authorMapper.deleteAll();
		
		authorMapper.authorEnroll(localAuthorInfo);
		authorMapper.authorEnroll(foreignAuthorInfo);
	}
	
	@After
	public void afterMethod() {
		authorMapper.deleteAll();
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void 관리자_작가관리_페이지_호출_테스트() throws Exception {
		mock.perform(get("/admin/authorManage"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(model().attributeExists("listData"))
		.andExpect(view().name("admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_작가관리_페이지에서_존재하지않는_작가이름을_검색한_경우_테스트() throws Exception {
		mock.perform(get("/admin/authorManage")
				.param("keyword", "한"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", "empty"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_작가관리_페이지에서_존재하는_작가이름을_검색한_경우_테스트() throws Exception {
		mock.perform(get("/admin/authorManage")
				.param("keyword", "S"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_작가등록시_작가목록_팝업창_호출_테스트() throws Exception {
		// 상품 등록 페이지에서 실행됨
		mock.perform(get("/admin/authorSearch"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorSearch"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_작가등록시_존재하지않는_작가이름을_검색한_경우_테스트() throws Exception {
		mock.perform(get("/admin/authorSearch")
				.param("keyword", "한"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", "empty"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorSearch"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_작가등록시_존재하는_작가이름을_검색한_경우_테스트() throws Exception {
		mock.perform(get("/admin/authorSearch")
				.param("keyword", "하"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorSearch"))
		.andDo(print());
	}
}
