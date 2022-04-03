package com.spring.shop.service;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.mapper.FileMapper;
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
	
	@Autowired
	private FileMapper fileMapper;
	
	private static AuthorVO localAuthorInfo;
	private static AuthorVO foreignAuthorInfo;
	
	private static BookVO bookContainImage;
	private static BookVO bookWithoutImage;
	
	private static ImageInfoVO imageInfo;
	
	private static PageInfo pageInfo;
	
	@BeforeClass
	public static void setUp() {
		localAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("테스트1");
		localAuthorInfo.setAuthorProfile("테스터입니다.");
		
		foreignAuthorInfo = new AuthorVO();
		foreignAuthorInfo.setNationId("02");
		foreignAuthorInfo.setAuthorName("미국인");
		foreignAuthorInfo.setAuthorProfile("테스터입니다.");
		
		imageInfo = new ImageInfoVO
				.Builder()
				.uploadPath("test\\2022\\01\\12")
				.uuid("test")
				.fileName("test").build();
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		imageList.add(imageInfo);
		
		bookContainImage = new BookVO();
		bookWithoutImage = new BookVO();
		
		bookContainImage.setBookName("테스트책");
		bookContainImage.setPublicationDate("2022-01-10");
		bookContainImage.setPublisher("한국출판사");
		bookContainImage.setCategoryCode("104000");
		bookContainImage.setBookPrice(20000);
		bookContainImage.setBookStock(50);
		bookContainImage.setBookDiscount(0.2);
		bookContainImage.setBookIntro("책 소개 ");
		bookContainImage.setBookContents("책 목차 ");
		bookContainImage.setImagesList(imageList);
		
		bookWithoutImage.setBookName("미국의역사");
		bookWithoutImage.setPublicationDate("2022-01-12");
		bookWithoutImage.setPublisher("미국출판사");
		bookWithoutImage.setCategoryCode("104000");
		bookWithoutImage.setBookPrice(20000);
		bookWithoutImage.setBookStock(10);
		bookWithoutImage.setBookDiscount(0.4);
		bookWithoutImage.setBookIntro("책 소개 ");
		bookWithoutImage.setBookContents("책 목차 ");
		
		pageInfo = new PageInfo(1, 10);
	}
	
	@Before
	public void beforeMethod() throws Exception {
		fileMapper.deleteAll();
		bookService.deleteAll();
		authorMapper.deleteAll();
		
		authorMapper.authorEnroll(localAuthorInfo);
		assertThat(authorMapper.getCount(), is(1));
		
		bookContainImage.setAuthorId(authorMapper.getLastPK());
		bookWithoutImage.setAuthorId(authorMapper.getLastPK());
		
		bookService.goodsEnroll(bookContainImage);
		assertThat(bookService.getCount(), is(1));
		
		bookService.goodsEnroll(bookWithoutImage);
		assertThat(bookService.getCount(), is(2));
		assertThat(fileMapper.getCount(), is(1));
		
		authorMapper.authorEnroll(foreignAuthorInfo);
		assertThat(authorMapper.getCount(), is(2));
	}
	
	@After
	public void afterMethod() {
		pageInfo = new PageInfo(1, 10);
		
		fileMapper.deleteAll();
		bookService.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void 작가검색_테스트() throws Exception {
		pageInfo.setType("A");
		pageInfo.setKeyword("테");
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
		
		assertThat(list.get(0).getBookName(), is(bookContainImage.getBookName()));
		assertThat(list.get(0).getImagesList().get(0).getFileName(), is(bookContainImage.getImagesList().get(0).getFileName()));
	}
	
	@Test
	public void 제목_공백_검색_테스트() throws Exception {
		pageInfo.setType("T");
		pageInfo.setKeyword(" ");
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
		
	}
	
	@Test
	public void 제목_키워드없이_검색_테스트() throws Exception {
		pageInfo.setType("T");
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
		
	}
	
	@Test
	public void 상품제목에_해당하지_않는_키워드로_제목검색_테스트() throws Exception {
		pageInfo.setType("T");
		pageInfo.setKeyword("신");
		
		List<BookVO> list = bookSearchService.getGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
		
	}
	
	@Test
	public void 작가검색_조건없을경우_상품_개수출력_테스트() throws Exception {
		
		int listCount = bookSearchService.getGoodsTotal(pageInfo);
		
		assertThat(listCount, is(2));
		
	}
	
	@Test
	public void 작가검색_작가이름키워드일경우_상품_개수출력_테스트() throws Exception {
		pageInfo.setType("A");
		pageInfo.setKeyword("미");
		
		int listCount = bookSearchService.getGoodsTotal(pageInfo);
		
		assertThat(listCount, is(0));
		
	}
	
	@Test
	public void 관리자페이지_상품제목에_해당하지_않는_키워드로_검색_테스트() throws Exception {
		pageInfo.setKeyword("-");
		
		List<BookVO> list = bookSearchService.adminPageGoodsList(pageInfo);
		
		assertThat(list.size(), is(0));
		
	}
	
	@Test
	public void 관리자페이지_검색_테스트() throws Exception {
		List<BookVO> list = bookSearchService.adminPageGoodsList(pageInfo);
		
		assertThat(list.size(), is(2));
		
	}
	
	@Test
	public void 관리자페이지_검색_상품_개수_테스트() throws Exception {
		int goodsTotal = bookSearchService.adminPageGoodsTotal(pageInfo);
		
		assertThat(goodsTotal, is(2));
		
	}
}
