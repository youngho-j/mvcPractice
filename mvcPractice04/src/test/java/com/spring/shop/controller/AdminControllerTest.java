package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
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
		.andExpect(model().attributeExists("categoryList"))
		.andDo(print());
	}
	@Test
	public void 관리자_상품관리_페이지_호출() throws Exception {
		mock.perform(get("/admin/goodsManage").session(session))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/goodsManage"))
		.andExpect(model().attributeExists("list"))
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
	public void 팝업창_테스트() throws Exception {
		mock.perform(get("/admin/authorSearch").session(session))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("list"))
		.andExpect(view().name("admin/authorSearch"))
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
	
	@Test
	public void 상품_상세페이지_이동_테스트() throws Exception {
		mock.perform(get("/admin/goodsDetail").session(session)
				.param("bookId", "8"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("categoryList"))
		.andExpect(model().attributeExists("goodsDetail"))
		.andExpect(view().name("admin/goodsDetail"))
		.andDo(print());
	}
	
	@Test
	public void 상품_수정페이지_이동_테스트() throws Exception {
		mock.perform(get("/admin/goodsModify").session(session)
				.param("bookId", "8"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("categoryList"))
		.andExpect(model().attributeExists("goodsDetail"))
		.andExpect(view().name("admin/goodsModify"))
		.andDo(print());
	}
	
	@Test
	public void 책_정보수정_테스트() throws Exception {
		mock.perform(post("/admin/goodsModify").session(session)
				.param("bookId", "8")
				.param("bookName", "한눈에쏙한국사")
				.param("authorId", "22")
				.param("nationId", "02")
				.param("publicationDate", "2021-10-14")
				.param("publisher", "한빛")
				.param("categoryCode", "106003")
				.param("bookPrice", "30000")
				.param("bookStock", "15")
				.param("bookDiscount", "0.2")
				.param("bookIntro", "<p>테스트</p>")
				.param("bookContents", "<p>테스트</p>"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("modifyResult"))
		.andExpect(redirectedUrl("/admin/goodsManage"))
		.andDo(print());
	}
	
	@Test
	public void 책_정보삭제_테스트() throws Exception {
		mock.perform(post("/admin/goodsDelete").session(session)
				.param("bookId", "9"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("deleteResult"))
		.andExpect(redirectedUrl("/admin/goodsManage"))
		.andDo(print());
	}
	
	@Test
	public void 파일_서버전달_테스트() throws Exception {
		String fileName = "book2.png";
		String filePath = "C:/Users/admin/Desktop/book2.png";
		
		MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, new FileInputStream(new File(filePath)));
		
		mock.perform(multipart("/admin/ajaxUpload").file(mockMultipartFile).session(session))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
}
