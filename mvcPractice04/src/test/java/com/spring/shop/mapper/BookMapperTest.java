package com.spring.shop.mapper;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
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
	
	private BookVO book1;
	private BookVO book2;
	private BookVO book3;
	
	private AuthorVO author1;
	
	private int authorId;
	
	@Before
	public void setUp() {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
		
		// 작가 id와 외래키로 연동되어 있어 메서드 실행 전 작가 등록 필요
		author1 = new AuthorVO();
		
		author1.setNationId("01");
		author1.setAuthorName("테스트1");
		author1.setAuthorProfile("테스터입니다.");
		
		authorMapper.authorEnroll(author1);
		
		authorId = authorMapper.getLastPK();
		
		book1 = new BookVO();
		book2 = new BookVO();
		book3 = new BookVO();
		
		book1.setBookName("테스트책");
		book1.setAuthorId(authorId);
		book1.setPublicationDate("2022-01-10");
		book1.setPublisher("한국출판사");
		book1.setCategoryCode("104000");
		book1.setBookPrice(20000);
		book1.setBookStock(50);
		book1.setBookDiscount(0.2);
		book1.setBookIntro("책 소개 ");
		book1.setBookContents("책 목차 ");
		
		book2.setBookName("테스트책2");
		book2.setAuthorId(authorId);
		book2.setPublicationDate("2022-01-10");
		book2.setPublisher("미국출판사");
		book2.setCategoryCode("104000");
		book2.setBookPrice(20000);
		book2.setBookStock(10);
		book2.setBookDiscount(0.4);
		book2.setBookIntro("책 소개 ");
		book2.setBookContents("책 목차 ");
		
		book3.setBookName("테스트책3");
		book3.setAuthorId(101);
		book3.setPublicationDate("2022-01-10");
		book3.setPublisher("미국출판사");
		book3.setCategoryCode("104000");
		book3.setBookPrice(20000);
		book3.setBookStock(10);
		book3.setBookDiscount(0.4);
		book3.setBookIntro("책 소개 ");
		book3.setBookContents("책 목차 ");
	}
	
	@After
	public void afterTest() {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		bookMapper.deleteAll();
		assertThat(bookMapper.getCount(), is(0));
		
		bookMapper.bookEnroll(book1);
		assertThat(bookMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 상품_등록_작가id_외래키_테스트() throws Exception {
		
		int result = bookMapper.bookEnroll(book3);
		
		assertThat(result, is(0));
		
		assertThat(authorMapper.getCount(), is(0));
	}
	
	@Test
	public void 상품_등록_테스트() throws Exception {
		
		int result = bookMapper.bookEnroll(book1);
		
		assertThat(result, is(1));
		
		assertThat(bookMapper.getCount(), is(1));
	}
	
	@Test
	public void 상품_상세정보_테스트() throws Exception {
		
		bookMapper.bookEnroll(book1);
		
		BookVO bookDetail = bookMapper.goodsDetail(bookMapper.getLastPK());
		
		assertThat(bookDetail.getBookName(), is(book1.getBookName()));
	}
	
	@Test
	public void 상품_정보수정_테스트() throws Exception {
		
		bookMapper.bookEnroll(book1);
		
		int primaryKey = bookMapper.getLastPK();
		
		BookVO bookDetail = bookMapper.goodsDetail(primaryKey);
		
		book2.setBookId(primaryKey);
		
		int result = bookMapper.goodsModify(book2);
		
		assertThat(result, is(1));
		
		BookVO modifyDetail = bookMapper.goodsDetail(primaryKey);
		
		assertNotEquals(bookDetail.getBookName(), modifyDetail.getBookName());
	}
	
	@Test
	public void 상품_정보삭제_테스트() throws Exception {
		bookMapper.bookEnroll(book1);
		
		assertThat(bookMapper.getCount(), is(1));
		
		int primaryKey = bookMapper.getLastPK();
		
		int result = bookMapper.goodsDelete(primaryKey);
		
		assertThat(result, is(1));
		
		assertThat(bookMapper.getCount(), is(0));
	}
	
}
