package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
	
	private AuthorVO author1;
	private AuthorVO author2;
	private AuthorVO author3;
	
	private PageInfo pageInfo;
	
	@Before
	public void setUp() {
		author1 = new AuthorVO();
		author2 = new AuthorVO();
		author3 = new AuthorVO();
		
		author1.setNationId("01");
		author1.setAuthorName("service테스트1");
		author1.setAuthorProfile("테스터입니다.");
		
		author2.setNationId("02");
		author2.setAuthorName("service테스트2");
		author2.setAuthorProfile("테스터입니다.");
		
		author3.setNationId("01");
		author3.setAuthorName("service테스트312394328432057349587324320987413298746324958724");
		author3.setAuthorProfile("테스터입니다.");
		
		pageInfo = new PageInfo(1, 10);
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		authorSearchService.deleteAll();
		assertThat(authorSearchService.getCount(), is(0));
		
		authorService.authorEnroll(author1);
		assertThat(authorSearchService.getCount(), is(1));
		
		authorService.authorEnroll(author2);
		assertThat(authorSearchService.getCount(), is(2));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 작가_등록시_작가이름범위초과_예외처리_테스트() throws Exception {
		
		authorSearchService.deleteAll();
		
		assertThat(authorSearchService.getCount(), is(0));
		
		authorService.authorEnroll(author3);
	}
	
	@Test
	public void 작가등록_테스트() throws Exception {
		
		authorSearchService.deleteAll();
		
		assertThat(authorSearchService.getCount(), is(0));
		
		int result = authorService.authorEnroll(author1);
		
		assertThat(1, is(result));
		
		assertThat(authorSearchService.getCount(), is(1));
	}
	
	
	@Test
	public void 작가목록_테스트() throws Exception {
		authorSearchService.deleteAll();
		
		assertThat(authorSearchService.getCount(), is(0));
		
		authorService.authorEnroll(author1);
		authorService.authorEnroll(author2);
		
		assertThat(authorSearchService.getCount(), is(2));
		
		List<AuthorVO> list = authorSearchService.authorGetList(pageInfo);		
		
		assertFalse(list.isEmpty());
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 등록된_작가수_카운팅_테스트() throws Exception {
		authorSearchService.deleteAll();
		
		assertThat(authorSearchService.getCount(), is(0));
		
		authorService.authorEnroll(author1);
		authorService.authorEnroll(author2);
		
		int total = authorSearchService.authorGetTotal(pageInfo);
		
		assertThat(total, is(2));;
	}
}
