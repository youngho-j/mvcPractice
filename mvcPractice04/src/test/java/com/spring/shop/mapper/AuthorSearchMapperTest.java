package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class AuthorSearchMapperTest {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private AuthorSearchMapper authorSearchMapper;
	
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
		localAuthorInfo.setAuthorName("테스트1");
		localAuthorInfo.setAuthorProfile("테스터입니다.");
		
		foreignAuthorInfo.setNationId("02");
		foreignAuthorInfo.setAuthorName("테스트2");
		foreignAuthorInfo.setAuthorProfile("테스터입니다.");
		
		wrongAuthorInfo.setNationId("23534654365443653968450968402437329847");
		wrongAuthorInfo.setAuthorName("테스트1");
		wrongAuthorInfo.setAuthorProfile("테스터입니다.");
	
		pageInfo = new PageInfo(1, 10);
	}
	
	@Before
	public void beforeMethod() {
		authorMapper.deleteAll();		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@After
	public void afterMethod() {
		authorMapper.deleteAll();		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		assertThat(authorSearchMapper.getCount(), is(1));
		
		authorMapper.authorEnroll(foreignAuthorInfo);
		assertThat(authorSearchMapper.getCount(), is(2));
	}
	
	@Test
	public void 작가_등록_메서드_테스트() throws Exception {
		int result = authorMapper.authorEnroll(localAuthorInfo);
		
		assertThat(1, is(result));
		
		assertThat(authorSearchMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 작가_등록시_컬럼값_초과_예외처리_테스트() throws Exception {
		authorMapper.authorEnroll(wrongAuthorInfo);
	}

	@Test
	public void 작가목록_존재하지_않응경우_출력_메서드_테스트() throws Exception {
		List<AuthorVO> list = authorSearchMapper.authorGetList(pageInfo);		
		
		assertTrue(list.isEmpty());
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void 작가목록_출력_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		authorMapper.authorEnroll(foreignAuthorInfo);
		
		assertThat(authorSearchMapper.getCount(), is(2));
		
		List<AuthorVO> list = authorSearchMapper.authorGetList(pageInfo);		
		
		assertFalse(list.isEmpty());
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 등록된_작가수_없는경우_카운팅_메서드_테스트() throws Exception {
		assertThat(authorSearchMapper.getCount(), is(0));
		
		int total = authorSearchMapper.authorGetTotal(pageInfo);
		
		assertThat(total, is(0));;
	}
	
	@Test
	public void 등록된_작가수_카운팅_메서드_테스트() throws Exception {
		authorMapper.authorEnroll(localAuthorInfo);
		authorMapper.authorEnroll(foreignAuthorInfo);
		
		int total = authorSearchMapper.authorGetTotal(pageInfo);
		
		assertThat(total, is(2));;
	}
}
