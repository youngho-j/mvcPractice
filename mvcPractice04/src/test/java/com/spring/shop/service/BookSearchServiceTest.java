package com.spring.shop.service;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class BookSearchServiceTest {
	
	@Autowired
	private BookSearchService bookSearchService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private AuthorVO author1;
	
	private BookVO book1;
	private BookVO book2;
	private BookVO book3;
	
	private ImageInfoVO testImage1;
	
	private PageInfo pageInfo;
	
	@Before
	public void setUp() {
		bookSearchService.deleteAll();
		authorMapper.deleteAll();
		
		author1 = new AuthorVO();
		
		author1.setNationId("01");
		author1.setAuthorName("테스트1");
		author1.setAuthorProfile("테스터입니다.");
		
		authorMapper.authorEnroll(author1);
		
		int authorId = authorMapper.getLastPK();
		
		testImage1 = new ImageInfoVO
				.Builder()
				.uploadPath("test\\2022\\01\\12")
				.uuid("test")
				.fileName("test").build();
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		imageList.add(testImage1);
		
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
		book1.setImagesList(imageList);
		
		book2.setBookName("테스트책2");
		book2.setAuthorId(101);
		book2.setPublicationDate("2022-01-10");
		book2.setPublisher("미국출판사");
		book2.setCategoryCode("104000");
		book2.setBookPrice(20000);
		book2.setBookStock(10);
		book2.setBookDiscount(0.4);
		book2.setBookIntro("책 소개 ");
		book2.setBookContents("책 목차 ");
		
		book3.setBookName("테스트책3");
		book3.setAuthorId(authorId);
		book3.setPublicationDate("2022-01-12");
		book3.setPublisher("미국출판사");
		book3.setCategoryCode("104000");
		book3.setBookPrice(20000);
		book3.setBookStock(10);
		book3.setBookDiscount(0.4);
		book3.setBookIntro("책 소개 ");
		book3.setBookContents("책 목차 ");
		
		pageInfo = new PageInfo(1, 10);
		pageInfo.setKeyword("테");
		pageInfo.setType("A");
	}
	
	@After
	public void afterTest() {
		bookService.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void 작가검색_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(1));
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(1));
		
		assertThat(list.get(0).getBookName(), is(book3.getBookName()));
	}
	
	@Test
	public void 작가검색_테스트2() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		bookService.goodsEnroll(book1);
		
		assertThat(bookService.getCount(), is(2));
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(1));
		
		assertThat(list.get(0).getImagesList().get(0).getFileName(), is(book1.getImagesList().get(0).getFileName()));
	}
	
	@Test
	public void 제목검색_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		pageInfo.setType("T");
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
		
	}
	
	@Test
	public void 상품제목에_해당하지_않는_키워드로_제목검색_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		pageInfo.setType("T");
		pageInfo.setKeyword("신");
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
		
	}
	
	@Test
	public void 작가검색_상품_개수출력_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		int listCount = bookSearchService.getGoodsTotal(pageInfo);
		
		assertThat(listCount, is(2));
		
	}
	
	@Test
	public void 제목검색_상품_개수출력_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		pageInfo.setType("T");
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		int listCount = bookSearchService.getGoodsTotal(pageInfo);
		
		assertThat(listCount, is(2));
		
	}
	
	@Test
	public void 관리자페이지_상품제목에_해당하지_않는_키워드로_검색_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		pageInfo.setKeyword("-");
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		List<BookVO> list = bookSearchService.adminPageGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
		
	}
	
	@Test
	public void 관리자페이지_검색_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		List<BookVO> list = bookSearchService.adminPageGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
		
	}
	
	@Test
	public void 관리자페이지_검색_상품_개수_테스트() throws Exception {
		assertThat(bookService.getCount(), is(0));		
		
		bookService.goodsEnroll(book1);
		bookService.goodsEnroll(book3);
		
		assertThat(bookService.getCount(), is(3));
		
		int goodsTotal = bookSearchService.adminPageGoodsTotal(pageInfo);
		
		assertThat(goodsTotal, is(2));
		
	}
}
