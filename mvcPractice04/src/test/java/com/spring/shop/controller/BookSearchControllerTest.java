package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.core.Is.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.mapper.BookMapper;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class BookSearchControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	private MockMvc mock;
	
	private static AuthorVO localAuthorInfo;
	
	private static BookVO cookingBookInfo;
	
	private static BookVO novelBookInfo;
	
	private int authorId;
	
	@BeforeClass
	public static void setUp() throws Exception {
		localAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("김하나");
		localAuthorInfo.setAuthorProfile("작가입니다.");

		cookingBookInfo = new BookVO();
		novelBookInfo = new BookVO();
		
		cookingBookInfo.setBookName("누구나할수있는요리");
		cookingBookInfo.setPublicationDate("2022-04-10");
		cookingBookInfo.setPublisher("한국출판사");
		cookingBookInfo.setCategoryCode("104002");
		cookingBookInfo.setBookPrice(20000);
		cookingBookInfo.setBookStock(50);
		cookingBookInfo.setBookDiscount(0.2);
		cookingBookInfo.setBookIntro("쉬운요리");
		cookingBookInfo.setBookContents("뱅쇼만들기");
		
		novelBookInfo.setBookName("종이여자");
		novelBookInfo.setPublicationDate("2022-03-15");
		novelBookInfo.setPublisher("길벗출판사");
		novelBookInfo.setCategoryCode("101001");
		novelBookInfo.setBookPrice(15000);
		novelBookInfo.setBookStock(10);
		novelBookInfo.setBookDiscount(0.3);
		novelBookInfo.setBookIntro("소설");
		novelBookInfo.setBookContents("챕터1");
	}
	
	@Before
	public void beforeMethod() throws Exception {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
		
		this.mock = MockMvcBuilders
				.webAppContextSetup(wac)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		//작가 등록
		authorMapper.authorEnroll(localAuthorInfo);
		
		authorId = authorMapper.getLastPK();
		
		cookingBookInfo.setAuthorId(authorId);
		novelBookInfo.setAuthorId(authorId);
		
		// 책 등록
		bookMapper.bookEnroll(cookingBookInfo);
		bookMapper.bookEnroll(novelBookInfo);
	}
	
	@After
	public void afterMethod() {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	@WithAnonymousUser
	public void 상품검색시_검색버튼만_누른경우_테스트() throws Exception {
		mock.perform(get("/search"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))		
		.andExpect(model().attributeExists("pagingManager"))		
		.andExpect(model().size(5))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithAnonymousUser
	public void 상품검색시_제목과_일치하는_책이_있는경우_테스트() throws Exception {
		mock.perform(get("/search")
				.param("type", "T")
				.param("keyword", "요"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))		
		.andExpect(model().attributeExists("pagingManager"))		
		.andExpect(model().size(5))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithAnonymousUser
	public void 상품검색시_제목과_일치하는_책이_없는경우_테스트() throws Exception {
		mock.perform(get("/search")
				.param("type", "T")
				.param("keyword", "테"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", is("empty")))		
		.andExpect(model().size(4))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithAnonymousUser
	public void 상품검색시_카테고리에_해당하는_책이_없는경우_테스트() throws Exception {
		mock.perform(get("/search")
				.param("type", "C")
				.param("categoryCode", "101000"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", is("empty")))		
		.andExpect(model().size(4))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithAnonymousUser
	public void 상품검색시_카테고리에_해당하는_책이_있는경우_테스트() throws Exception {
		mock.perform(get("/search")
				.param("type", "C")
				.param("categoryCode", "101001"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))		
		.andExpect(model().attributeExists("pagingManager"))		
		.andExpect(model().size(5))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithMockUser(username = "testUser", roles = {"ADMIN"})
	public void 관리자페이지_상품관리_페이지_이동_테스트() throws Exception {
		mock.perform(get("/admin/goodsManage"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))		
		.andExpect(model().size(3))		
		.andExpect(view().name("admin/goodsManage"))
		.andDo(print());
	}
	
	@Test
	@WithMockUser(username = "testUser", roles = {"ADMIN"})
	public void 관리자페이지_상품검색시_제목과_일치하는_상품이_없는경우_테스트() throws Exception {
		mock.perform(get("/admin/goodsManage")
				.param("type", "T")
				.param("keyword", "테"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", is("empty")))		
		.andExpect(model().size(2))		
		.andExpect(view().name("admin/goodsManage"))
		.andDo(print());
	}
}
