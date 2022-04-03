package com.spring.shop.service;

import static org.junit.Assert.*;

import org.junit.After;

import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class AuthorServiceTest {
	
	@Autowired
	private AuthorService authorService;
	
	private static AuthorVO localAuthorInfo;
	private static AuthorVO foreignAuthorInfo;
	private static AuthorVO wrongAuthorInfo;
	
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
	}
	
	@Before
	public void beforeMethod() {
		authorService.deleteAll();
		assertThat(authorService.getCount(), is(0));
	}
	
	@After
	public void afterAction() {
		authorService.deleteAll();
		assertThat(authorService.getCount(), is(0));
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		authorService.authorEnroll(localAuthorInfo);
		assertThat(authorService.getCount(), is(1));
		
		authorService.authorEnroll(foreignAuthorInfo);
		assertThat(authorService.getCount(), is(2));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 작가_등록시_작가이름범위초과_예외처리_테스트() throws Exception {
		authorService.authorEnroll(wrongAuthorInfo);
	}
	
	@Test
	public void 작가등록_테스트() throws Exception {
		int result = authorService.authorEnroll(localAuthorInfo);
		
		assertThat(result, is(1));
		
		assertThat(authorService.getCount(), is(1));
	}
	
	@Test
	public void 작가_상세정보_테스트() throws Exception {
		authorService.authorEnroll(localAuthorInfo);
		
		AuthorVO authorDetail = authorService.authorGetDetail(authorService.getLastPK());
		
		assertThat(localAuthorInfo.getAuthorName(), is(authorDetail.getAuthorName()));
	}
	
	@Test
	public void 작가_정보_수정_테스트() throws Exception {
		authorService.authorEnroll(localAuthorInfo);
		
		int primaryKey = authorService.getLastPK();
		
		AuthorVO authorDetail = authorService.authorGetDetail(primaryKey);
		
		foreignAuthorInfo.setAuthorId(primaryKey);
		
		int result = authorService.authorModify(foreignAuthorInfo);
		
		AuthorVO ModifyDetail = authorService.authorGetDetail(primaryKey);
		
		assertThat(result, is(1));
		assertNotEquals(authorDetail.getAuthorName(), ModifyDetail.getAuthorName());
	}
	
	@Test
	public void 작가정보_삭제_테스트() throws Exception {
		authorService.authorEnroll(localAuthorInfo);
		
		int primaryKey = authorService.getLastPK();
		
		int result = authorService.authorDelete(primaryKey);
		
		assertThat(result, is(1));
	}
}
