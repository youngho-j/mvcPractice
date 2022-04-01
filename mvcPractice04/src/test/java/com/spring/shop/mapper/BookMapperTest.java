package com.spring.shop.mapper;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class BookMapperTest {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private static BookVO bookWithoutAuthorId1;
	private static BookVO bookWithoutAuthorId2;
	private static BookVO bookWithWrongAuthorId;
	
	private static AuthorVO authorInfo;
	
	@BeforeClass
	public static void setUp() {
		// 작가 id와 외래키로 연동되어 있어 메서드 실행 전 작가 등록 필요
		authorInfo = new AuthorVO();
		
		authorInfo.setNationId("01");
		authorInfo.setAuthorName("테스트1");
		authorInfo.setAuthorProfile("테스터입니다.");
		
		bookWithoutAuthorId1 = new BookVO();
		bookWithoutAuthorId2 = new BookVO();
		bookWithWrongAuthorId = new BookVO();
		
		bookWithoutAuthorId1.setBookName("테스트책");
		bookWithoutAuthorId1.setPublicationDate("2022-01-10");
		bookWithoutAuthorId1.setPublisher("한국출판사");
		bookWithoutAuthorId1.setCategoryCode("104000");
		bookWithoutAuthorId1.setBookPrice(20000);
		bookWithoutAuthorId1.setBookStock(50);
		bookWithoutAuthorId1.setBookDiscount(0.2);
		bookWithoutAuthorId1.setBookIntro("책 소개 ");
		bookWithoutAuthorId1.setBookContents("책 목차 ");
		
		bookWithoutAuthorId2.setBookName("테스트책2");
		bookWithoutAuthorId2.setPublicationDate("2022-01-10");
		bookWithoutAuthorId2.setPublisher("미국출판사");
		bookWithoutAuthorId2.setCategoryCode("104000");
		bookWithoutAuthorId2.setBookPrice(20000);
		bookWithoutAuthorId2.setBookStock(10);
		bookWithoutAuthorId2.setBookDiscount(0.4);
		bookWithoutAuthorId2.setBookIntro("책 소개 ");
		bookWithoutAuthorId2.setBookContents("책 목차 ");
		
		bookWithWrongAuthorId.setBookName("테스트책3");
		bookWithWrongAuthorId.setAuthorId(101);
		bookWithWrongAuthorId.setPublicationDate("2022-01-10");
		bookWithWrongAuthorId.setPublisher("미국출판사");
		bookWithWrongAuthorId.setCategoryCode("104000");
		bookWithWrongAuthorId.setBookPrice(20000);
		bookWithWrongAuthorId.setBookStock(10);
		bookWithWrongAuthorId.setBookDiscount(0.4);
		bookWithWrongAuthorId.setBookIntro("책 소개 ");
		bookWithWrongAuthorId.setBookContents("책 목차 ");
	}
	
	@Before
	public void beforeMethod() {
		bookMapper.deleteAll();
		assertThat(bookMapper.getCount(), is(0));
		
		authorMapper.authorEnroll(authorInfo);
		
		bookWithoutAuthorId1.setAuthorId(authorMapper.getLastPK());
		bookWithoutAuthorId2.setAuthorId(authorMapper.getLastPK());
	}
	
	@After
	public void afterMethod() {
		bookMapper.deleteAll();
		assertThat(bookMapper.getCount(), is(0));
		
		authorMapper.deleteAll();
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		bookMapper.bookEnroll(bookWithoutAuthorId1);
		assertThat(bookMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 상품_등록_작가id_외래키_테스트() throws Exception {
		int result = bookMapper.bookEnroll(bookWithWrongAuthorId);
		
		assertThat(result, is(0));
		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void 상품_등록_테스트() throws Exception {
		int result = bookMapper.bookEnroll(bookWithoutAuthorId1);
		
		assertThat(result, is(1));
		
		assertThat(bookMapper.getCount(), is(1));
	}
	
	@Test
	public void 상품_상세정보_테스트() throws Exception {
		bookMapper.bookEnroll(bookWithoutAuthorId1);
		
		BookVO bookDetail = bookMapper.goodsDetail(bookMapper.getLastPK());
		
		assertThat(bookDetail.getBookName(), is(bookWithoutAuthorId1.getBookName()));
	}
	
	@Test
	public void 상품_정보수정_테스트() throws Exception {
		bookMapper.bookEnroll(bookWithoutAuthorId1);
		
		int primaryKey = bookMapper.getLastPK();
		
		BookVO bookDetail = bookMapper.goodsDetail(primaryKey);
		
		bookWithoutAuthorId2.setBookId(primaryKey);
		
		int result = bookMapper.goodsModify(bookWithoutAuthorId2);
		
		assertThat(result, is(1));
		
		BookVO modifyDetail = bookMapper.goodsDetail(primaryKey);
		
		assertNotEquals(bookDetail.getBookName(), modifyDetail.getBookName());
	}
	
	@Test
	public void 상품_정보삭제_테스트() throws Exception {
		bookMapper.bookEnroll(bookWithoutAuthorId1);
		
		assertThat(bookMapper.getCount(), is(1));
		
		int primaryKey = bookMapper.getLastPK();
		
		int result = bookMapper.goodsDelete(primaryKey);
		
		assertThat(result, is(1));
		
		assertThat(bookMapper.getCount(), is(0));
	}
	
}
