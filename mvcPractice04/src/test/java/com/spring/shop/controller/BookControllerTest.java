package com.spring.shop.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.mapper.BookMapper;
import com.spring.shop.mapper.FileMapper;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class BookControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private FileMapper fileMapper;
	
	private static AuthorVO localAuthorInfo;
	
	private static BookVO bookWithoutAuthorId;
	
	private MockMvc mock;
	
	private int authorId;
	
	private String bookId;
	
	@BeforeClass
	public static void setUp() throws Exception {
		localAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("테스트1");
		localAuthorInfo.setAuthorProfile("테스터입니다.");

		bookWithoutAuthorId = new BookVO();
		
		bookWithoutAuthorId.setBookName("테스트책");
		bookWithoutAuthorId.setPublicationDate("2022-02-10");
		bookWithoutAuthorId.setPublisher("한국출판사");
		bookWithoutAuthorId.setCategoryCode("104000");
		bookWithoutAuthorId.setBookPrice(20000);
		bookWithoutAuthorId.setBookStock(50);
		bookWithoutAuthorId.setBookDiscount(0.2);
		bookWithoutAuthorId.setBookIntro("책 소개 ");
		bookWithoutAuthorId.setBookContents("책 목차 ");
	}
	
	@Before
	public void beforeMethod() throws Exception {
		fileMapper.deleteAll();
		bookMapper.deleteAll();
		authorMapper.deleteAll();
		
		this.mock = MockMvcBuilders.webAppContextSetup(wac).build();
		
		authorMapper.authorEnroll(localAuthorInfo);
		
		authorId = authorMapper.getLastPK();
		
		bookWithoutAuthorId.setAuthorId(authorId);
		
		bookMapper.bookEnroll(bookWithoutAuthorId);
		
		bookId = Integer.toString(bookMapper.getLastPK());
	}
	
	@After
	public void afterAction() {
		fileMapper.deleteAll();
		bookMapper.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void 관리자_상품등록_페이지_호출() throws Exception {
		mock.perform(get("/admin/goodsEnroll"))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/goodsEnroll"))
		.andExpect(model().attributeExists("categoryList"))
		.andDo(print());
	}
	
	@Test
	public void 상품_상세페이지_이동_테스트() throws Exception {
		mock.perform(get("/admin/goodsDetail")
				.param("bookId", bookId))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("categoryList"))
		.andExpect(model().attributeExists("goodsDetail"))
		.andExpect(view().name("admin/goodsDetail"))
		.andDo(print());
	}
	
	@Test
	public void 상품_수정페이지_이동_테스트() throws Exception {
		mock.perform(get("/admin/goodsModify")
				.param("bookId", bookId))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("categoryList"))
		.andExpect(model().attributeExists("goodsDetail"))
		.andExpect(view().name("admin/goodsModify"))
		.andDo(print());
	}
	
	@Test
	public void 책_등록_테스트() throws Exception {
		// view에 csrf 추가 필요
		mock.perform(post("/admin/goodsEnroll").with(csrf())
				.param("bookName", "controllerTest")
				.param("authorId", Integer.toString(authorId))
				.param("nationId", "01")
				.param("publicationDate", "2021-10-21")
				.param("publisher", "테스트")
				.param("categoryCode", "104000")
				.param("bookPrice", "12000")
				.param("bookStock", "10")
				.param("bookDiscount", "0.2")
				.param("bookIntro", "test")
				.param("bookContents", "test")
				.param("imagesList[0].uploadPath", "H:\\mvcPractice04upload\\2021\\11\\17")
				.param("imagesList[0].uuid", "e63cb514-24d5-4f0c-9bdd-ba7969sda95c0")
				.param("imagesList[0].fileName", "mbook2.png"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("enrollResult"))
		.andExpect(redirectedUrl("/admin/goodsManage"))
		.andDo(print());
	}
	
	@Test
	public void 책_정보수정_테스트() throws Exception {
		// view에 csrf 추가 필요
		mock.perform(post("/admin/goodsModify").with(csrf())
				.param("bookId", bookId)
				.param("bookName", "수정테스트")
				.param("authorId", Integer.toString(authorId))
				.param("nationId", "01")
				.param("publicationDate", "2021-10-21")
				.param("publisher", "한빛")
				.param("categoryCode", "104000")
				.param("bookPrice", "30000")
				.param("bookStock", "15")
				.param("bookDiscount", "0.2")
				.param("bookIntro", "<p>수정 테스트</p>")
				.param("bookContents", "<p>수정 테스트</p>"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attributeExists("modifyResult"))
		.andExpect(redirectedUrl("/admin/goodsManage"))
		.andDo(print());
	}
	
	@Test
	public void 이미지_없는_책_정보삭제_테스트() throws Exception {
		mock.perform(post("/admin/goodsDelete")
				.param("bookId", bookId))
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/admin/goodsManage"))
		.andDo(print());
	}
}
