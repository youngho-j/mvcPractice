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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class BookSearchMapperTest {
	
	@Autowired
	private BookSearchMapper bookSearchMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private static BookVO bookWithoutAuthorId1;
	private static BookVO bookWithoutAuthorId2;
	
	private static AuthorVO authorInfo;
	
	private static PageInfo pageInfo;
	
	@BeforeClass
	public static void setUp() {
		// 작가 id와 외래키로 연동되어 있어 메서드 실행 전 작가 등록 필요
		authorInfo = new AuthorVO();
		
		authorInfo.setNationId("01");
		authorInfo.setAuthorName("테스트1");
		authorInfo.setAuthorProfile("테스터입니다.");
		
		bookWithoutAuthorId1 = new BookVO();
		
		bookWithoutAuthorId1.setBookName("테스트책");
		bookWithoutAuthorId1.setPublicationDate("2022-01-10");
		bookWithoutAuthorId1.setPublisher("한국출판사");
		bookWithoutAuthorId1.setCategoryCode("104000");
		bookWithoutAuthorId1.setBookPrice(20000);
		bookWithoutAuthorId1.setBookStock(50);
		bookWithoutAuthorId1.setBookDiscount(0.2);
		bookWithoutAuthorId1.setBookIntro("책 소개 ");
		bookWithoutAuthorId1.setBookContents("책 목차 ");
		
		bookWithoutAuthorId2 = new BookVO();
		
		bookWithoutAuthorId2.setBookName("테스트책2");
		bookWithoutAuthorId2.setPublicationDate("2022-01-10");
		bookWithoutAuthorId2.setPublisher("미국출판사");
		bookWithoutAuthorId2.setCategoryCode("104000");
		bookWithoutAuthorId2.setBookPrice(20000);
		bookWithoutAuthorId2.setBookStock(10);
		bookWithoutAuthorId2.setBookDiscount(0.4);
		bookWithoutAuthorId2.setBookIntro("책 소개 ");
		bookWithoutAuthorId2.setBookContents("책 목차 ");
		
		pageInfo = new PageInfo(1, 10);
	}
	
	@Before
	public void beforeMethod() throws Exception{
		authorMapper.authorEnroll(authorInfo);
		
		bookWithoutAuthorId1.setAuthorId(authorMapper.getLastPK());
		bookWithoutAuthorId2.setAuthorId(authorMapper.getLastPK());
		
		bookMapper.bookEnroll(bookWithoutAuthorId1);
		bookMapper.bookEnroll(bookWithoutAuthorId2);
	}
	
	@After
	public void afterTest() {
		bookMapper.deleteAll();
		assertThat(bookMapper.getCount(), is(0));
		
		authorMapper.deleteAll();
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void 작가목록에_존재하지_않는_작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookSearchMapper.getAuthorIdList("1 or 1=1");
		
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void 작가목록에_존재하는_작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookSearchMapper.getAuthorIdList("테");
		
		assertThat(list.size(), is(1));
	}
	
	@Test
	public void 키워드_제목_검색_목록_리턴_테스트() throws Exception {
		pageInfo.setType("T");
		pageInfo.setKeyword("테스트");
		
		List<BookVO> list = bookSearchMapper.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 잘못된_키워드_작가_검색_목록_리턴_테스트() throws Exception {
		pageInfo.setType("A");
		pageInfo.setKeyword("!");
		
		List<String> authorList = bookSearchMapper.getAuthorIdList(pageInfo.getKeyword());
		
		pageInfo.setAuthorIdList(authorList);
		
		List<BookVO> list = bookSearchMapper.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void 작가_검색_목록_리턴_테스트() throws Exception {
		pageInfo.setType("A");
		pageInfo.setKeyword("테");
		
		List<String> authorList = bookSearchMapper.getAuthorIdList(pageInfo.getKeyword());
		
		pageInfo.setAuthorIdList(authorList);
		
		List<BookVO> list = bookSearchMapper.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 제목_작가_검색_목록_리턴_테스트() throws Exception {
		pageInfo.setType("AT");
		pageInfo.setKeyword("테");
		
		List<String> authorList = bookSearchMapper.getAuthorIdList(pageInfo.getKeyword());
		
		pageInfo.setAuthorIdList(authorList);
		
		List<BookVO> list = bookSearchMapper.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 제목_키워드_상품_전체_개수_리턴_테스트() throws Exception {
		pageInfo.setType("T");
		pageInfo.setKeyword("테");
		
		int result = bookSearchMapper.getGoodsTotal(pageInfo);
		
		assertTrue(result > 0);
	}
}
