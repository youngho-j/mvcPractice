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
		localAuthorInfo.setAuthorName("?????????");
		localAuthorInfo.setAuthorProfile("???????????????.");

		cookingBookInfo = new BookVO();
		novelBookInfo = new BookVO();
		
		cookingBookInfo.setBookName("???????????????????????????");
		cookingBookInfo.setPublicationDate("2022-04-10");
		cookingBookInfo.setPublisher("???????????????");
		cookingBookInfo.setCategoryCode("104002");
		cookingBookInfo.setBookPrice(20000);
		cookingBookInfo.setBookStock(50);
		cookingBookInfo.setBookDiscount(0.2);
		cookingBookInfo.setBookIntro("????????????");
		cookingBookInfo.setBookContents("???????????????");
		
		novelBookInfo.setBookName("????????????");
		novelBookInfo.setPublicationDate("2022-03-15");
		novelBookInfo.setPublisher("???????????????");
		novelBookInfo.setCategoryCode("101001");
		novelBookInfo.setBookPrice(15000);
		novelBookInfo.setBookStock(10);
		novelBookInfo.setBookDiscount(0.3);
		novelBookInfo.setBookIntro("??????");
		novelBookInfo.setBookContents("??????1");
	}
	
	@Before
	public void beforeMethod() throws Exception {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
		
		this.mock = MockMvcBuilders
				.webAppContextSetup(wac)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		//?????? ??????
		authorMapper.authorEnroll(localAuthorInfo);
		
		authorId = authorMapper.getLastPK();
		
		cookingBookInfo.setAuthorId(authorId);
		novelBookInfo.setAuthorId(authorId);
		
		// ??? ??????
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
	public void ???????????????_???????????????_????????????_?????????() throws Exception {
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
	public void ???????????????_?????????_????????????_??????_????????????_?????????() throws Exception {
		mock.perform(get("/search")
				.param("type", "T")
				.param("keyword", "???"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))		
		.andExpect(model().attributeExists("pagingManager"))		
		.andExpect(model().size(5))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithAnonymousUser
	public void ???????????????_?????????_????????????_??????_????????????_?????????() throws Exception {
		mock.perform(get("/search")
				.param("type", "T")
				.param("keyword", "???"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", is("empty")))		
		.andExpect(model().size(4))		
		.andExpect(view().name("user/search"))
		.andDo(print());
	}
	
	@Test
	@WithAnonymousUser
	public void ???????????????_???????????????_????????????_??????_????????????_?????????() throws Exception {
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
	public void ???????????????_???????????????_????????????_??????_????????????_?????????() throws Exception {
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
	public void ??????????????????_????????????_?????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/goodsManage"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("listData"))		
		.andExpect(model().size(3))		
		.andExpect(view().name("admin/goodsManage"))
		.andDo(print());
	}
	
	@Test
	@WithMockUser(username = "testUser", roles = {"ADMIN"})
	public void ??????????????????_???????????????_?????????_????????????_?????????_????????????_?????????() throws Exception {
		mock.perform(get("/admin/goodsManage")
				.param("type", "T")
				.param("keyword", "???"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("listData", is("empty")))
		.andExpect(model().attributeExists("pagingManager"))	
		.andExpect(model().size(3))		
		.andExpect(view().name("admin/goodsManage"))
		.andDo(print());
	}
}
