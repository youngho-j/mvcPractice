package com.spring.shop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

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
import org.springframework.web.util.NestedServletException;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@WithMockUser(username = "testUser", roles = {"ADMIN"})
public class AuthorControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private static AuthorVO localAuthorInfo;
	
	private MockMvc mock;
	
	private int authorId;
	
	@BeforeClass
	public static void setUp() {
		localAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("service?????????1");
		localAuthorInfo.setAuthorProfile("??????????????????.");
	}
	
	@Before
	public void beforeMethod() {
		this.mock = MockMvcBuilders
				.webAppContextSetup(wac)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		authorMapper.deleteAll();
		authorMapper.authorEnroll(localAuthorInfo);
		
		authorId = authorMapper.getLastPK();
	}
	
	@After
	public void afterAction() {
		authorMapper.deleteAll();
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void ?????????_????????????_?????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorEnroll"))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/authorEnroll"))
		.andDo(print());
	}
	
	@Test(expected = NestedServletException.class)
	public void ??????_?????????_??????????????????_??????_?????????() throws Exception {
		mock.perform(post("/admin/authorEnroll").with(csrf())
				.param("authorName", "test23093209askasdsadawfjqef121321sdagfknqewkfnqekfn3215kkvrknwq4kndskasfpkjq")
				.param("nationId", "02")
				.param("authorProfile", "test"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("enroll_result", is("?????? ????????? ?????????????????????.")))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ??????_??????_?????????() throws Exception {
		mock.perform(post("/admin/authorEnroll").with(csrf())
				.param("authorName", "test01")
				.param("nationId", "02")
				.param("authorProfile", "test"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("enroll_result", is("test01")))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ????????????_??????_??????id_?????????_???????????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorDetail")
				.param("authorId", "94"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("alertMsg", is("???????????? ?????? ?????? ID?????????.")))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ?????????_?????????_??????id_?????????_???????????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorDetail")
				.param("authorId", "test"))
		.andExpect(status().is4xxClientError())
		.andDo(print());
	}
	
	@Test
	public void ??????_???????????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorDetail")
				.param("authorId", Integer.toString(authorId)))
		.andExpect(status().isOk())
		.andExpect(view().name("/admin/authorDetail"))
		.andDo(print());
	}
	
	@Test
	public void ????????????_??????_??????_???????????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorModify")
				.param("authorId", "94"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("alertMsg", is("???????????? ?????? ?????? ID?????????.")))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ?????????_?????????_??????id_?????????_???????????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorModify")
				.param("authorId", "test"))
		.andExpect(status().is4xxClientError())
		.andDo(print());
	}
	
	@Test
	public void ??????_???????????????_??????_?????????() throws Exception {
		mock.perform(get("/admin/authorModify")
				.param("authorId", Integer.toString(authorId)))
		.andExpect(status().isOk())
		.andExpect(view().name("/admin/authorModify"))
		.andDo(print());
	}
	
	@Test
	public void ????????????_??????_??????_??????_??????_?????????() throws Exception {
		mock.perform(post("/admin/authorModify").with(csrf())
				.param("authorId", "94")
				.param("authorName", "test")
				.param("nationId", "02")
				.param("authorProfile", "test"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("modifyResult", is(0)))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ??????_??????_??????_?????????() throws Exception {
		mock.perform(post("/admin/authorModify").with(csrf())
				.param("authorId", Integer.toString(authorId))
				.param("authorName", "modify")
				.param("nationId", "01")
				.param("authorProfile", "modify"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("modifyResult", is(1)))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ??????_??????_??????_?????????() throws Exception {
		mock.perform(post("/admin/authorDelete").with(csrf())
				.param("authorId", Integer.toString(authorId)))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("deleteResult", is(1)))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
	
	@Test
	public void ??????????????????_??????_??????_??????_?????????() throws Exception {
		mock.perform(post("/admin/authorDelete").with(csrf())
				.param("authorId", "1"))
		.andExpect(status().is3xxRedirection())
		.andExpect(flash().attribute("deleteResult", is(0)))
		.andExpect(redirectedUrl("/admin/authorManage"))
		.andDo(print());
	}
}
