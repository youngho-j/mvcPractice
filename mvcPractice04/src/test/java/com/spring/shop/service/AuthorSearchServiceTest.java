package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class AuthorSearchServiceTest {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AuthorSearchService authorSearchService;
	
	private static AuthorVO localAuthorInfo;
	private static AuthorVO foreignAuthorInfo;
	private static AuthorVO wrongAuthorInfo;
	
	private static PageInfo pageInfo;
	
	@BeforeClass
	public static void setUp() {
		localAuthorInfo = new AuthorVO();
		foreignAuthorInfo = new AuthorVO();
		wrongAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("service테스트1");
		localAuthorInfo.setAuthorProfile("테스터입니다.");
		
		foreignAuthorInfo.setNationId("02");
		foreignAuthorInfo.setAuthorName("service테스트2");
		foreignAuthorInfo.setAuthorProfile("테스터입니다.");
		
		wrongAuthorInfo.setNationId("01");
		wrongAuthorInfo.setAuthorName("service테스트312394328432057349587324320987413298746324958724");
		wrongAuthorInfo.setAuthorProfile("테스터입니다.");
		
		pageInfo = new PageInfo(1, 10);
	}
	
	@Before
	public void beforeMethod() throws Exception {
		authorService.deleteAll();
		
		assertThat(authorSearchService.getCount(), is(0));
		
		authorService.authorEnroll(localAuthorInfo);
		authorService.authorEnroll(foreignAuthorInfo);
		
		assertThat(authorSearchService.getCount(), is(2));
	}
	
	@After
	public void afterMethod() throws Exception {
		authorService.deleteAll();
		assertThat(authorSearchService.getCount(), is(0));
	}
	
	@Test
	public void 작가목록_테스트() throws Exception {
		List<AuthorVO> list = authorSearchService.authorGetList(pageInfo);		
		
		assertFalse(list.isEmpty());
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 등록된_작가수_카운팅_테스트() throws Exception {
		int total = authorSearchService.authorGetTotal(pageInfo);
		
		assertThat(total, is(2));;
	}
}
