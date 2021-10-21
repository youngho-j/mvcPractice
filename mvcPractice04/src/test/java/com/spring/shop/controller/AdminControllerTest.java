package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.shop.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class AdminControllerTest {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	private MemberVO memberInfo;
	
	private MockHttpSession session;
	
	@Before
	public void setUp() {
		memberInfo = new MemberVO();
		
		memberInfo.setMemberId("admin");
		memberInfo.setAdminCk(1);
		
		session = new MockHttpSession();
		
		session.setAttribute("member", memberInfo);
		
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void 관리자_페이지_호출() throws Exception {
		mock.perform(get("/admin/main").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/main"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_상품등록_페이지_호출() throws Exception {
		mock.perform(get("/admin/goodsEnroll").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/goodsEnroll"))
		.andDo(print());
	}
	@Test
	public void 관리자_상품관리_페이지_호출() throws Exception {
		mock.perform(get("/admin/goodsManage").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/goodsManage"))
		.andDo(print());
	}
	@Test
	public void 관리자_작가등록_페이지_호출() throws Exception {
		mock.perform(get("/admin/authorEnroll").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/authorEnroll"))
		.andDo(print());
	}
	
	@Test
	public void 관리자_작가관리_페이지_호출() throws Exception {
		mock.perform(get("/admin/authorManage").session(session)
				.param("pageNum", "1")
				.param("viewPerPage", "10")
				.param("keyword", "곽"))
		.andExpect(status().isOk())
		.andExpect(model().attributeDoesNotExist("list"))
		.andExpect(model().attributeExists("checkResult"))
		.andExpect(model().attributeExists("pagingManager"))
		.andExpect(view().name("admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 작가_등록_테스트() throws Exception {
		mock.perform(post("/admin/authorEnroll").session(session)
				.param("authorName", "test")
				.param("nationId", "02")
				.param("authorProfile", "test")
				)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 작가_상세페이지_테스트() throws Exception {
		mock.perform(get("/admin/authorDetail").session(session)
				.param("authorId", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/authorDetail"))
		.andDo(print());
	}
	
	@Test
	public void 작가_수정페이지_테스트() throws Exception {
		mock.perform(get("/admin/authorModify").session(session)
				.param("authorId", "1"))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/authorModify"))
		.andDo(print());
	}
	
	@Test
	public void 작가_정보_수정_테스트() throws Exception {
		mock.perform(post("/admin/authorModify").session(session)
				.param("authorId", "2")
				.param("authorName", "test")
				.param("nationId", "02")
				.param("authorProfile", "test"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("modifyResult"))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void 책_등록_테스트() throws Exception {
		mock.perform(post("/admin/goodsEnroll").session(session)
				.param("bookName", "controllerTest")
				.param("authorId", "031")
				.param("nationId", "02")
				.param("publicationDate", "2021-10-21")
				.param("publisher", "테스트")
				.param("categoryCode", "0231")
				.param("bookPrice", "12000")
				.param("bookStock", "10")
				.param("bookDiscount", "0.2")
				.param("bookIntro", "test")
				.param("bookContents", "test"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("enrollResult"))
		.andExpect(redirectedUrl("/admin/goodsManage"))
		.andDo(print());
	}
	
}
