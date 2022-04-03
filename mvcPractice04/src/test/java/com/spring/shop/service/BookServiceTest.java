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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.CollectionUtils;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.mapper.FileMapper;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		{"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
			"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
			"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class BookServiceTest {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private static AuthorVO localAuthorInfo;
	
	private static BookVO bookContainImage;
	private static BookVO bookContainWrongInfo;
	private static BookVO bookWithoutImage;
	
	private static ImageInfoVO imageInfo;
	private static ImageInfoVO replaceImageInfo;
	
	@BeforeClass
	public static void setUp() {
		localAuthorInfo = new AuthorVO();
		
		localAuthorInfo.setNationId("01");
		localAuthorInfo.setAuthorName("테스트1");
		localAuthorInfo.setAuthorProfile("테스터입니다.");
		
		imageInfo = new ImageInfoVO
				.Builder()
				.uploadPath("test\\2022\\01\\12")
				.uuid("test")
				.fileName("test").build();
		
		replaceImageInfo = new ImageInfoVO
				.Builder()
				.uploadPath("test\\2022\\01\\30")
				.uuid("replace")
				.fileName("replace").build();
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		imageList.add(imageInfo);
		
		bookContainImage = new BookVO();
		bookContainWrongInfo = new BookVO();
		bookWithoutImage = new BookVO();
		
		bookContainImage.setBookName("이미지가포함된책");
		bookContainImage.setPublicationDate("2022-01-10");
		bookContainImage.setPublisher("한국출판사");
		bookContainImage.setCategoryCode("104000");
		bookContainImage.setBookPrice(20000);
		bookContainImage.setBookStock(50);
		bookContainImage.setBookDiscount(0.2);
		bookContainImage.setBookIntro("책 소개 ");
		bookContainImage.setBookContents("책 목차 ");
		bookContainImage.setImagesList(imageList);
		
		bookContainWrongInfo.setBookName("잘못된정보가입력된책");
		bookContainWrongInfo.setAuthorId(101);
		bookContainWrongInfo.setPublicationDate("2022-01-10");
		bookContainWrongInfo.setPublisher("미국출판사");
		bookContainWrongInfo.setCategoryCode("104000");
		bookContainWrongInfo.setBookPrice(20000);
		bookContainWrongInfo.setBookStock(10);
		bookContainWrongInfo.setBookDiscount(0.4);
		bookContainWrongInfo.setBookIntro("책 소개 ");
		bookContainWrongInfo.setBookContents("책 목차 ");
		
		bookWithoutImage.setBookName("이미지없는책");
		bookWithoutImage.setPublicationDate("2022-01-12");
		bookWithoutImage.setPublisher("미국출판사");
		bookWithoutImage.setCategoryCode("104000");
		bookWithoutImage.setBookPrice(20000);
		bookWithoutImage.setBookStock(10);
		bookWithoutImage.setBookDiscount(0.4);
		bookWithoutImage.setBookIntro("책 소개 ");
		bookWithoutImage.setBookContents("책 목차 ");
	}
	
	@Before
	public void beforeMethod() {
		fileMapper.deleteAll();
		bookService.deleteAll();
		authorMapper.deleteAll();
		
		authorMapper.authorEnroll(localAuthorInfo);
		assertThat(authorMapper.getCount(), is(1));
		
		bookContainImage.setAuthorId(authorMapper.getLastPK());
		bookWithoutImage.setAuthorId(authorMapper.getLastPK());
	}
	
	@After
	public void afterMethod() {
		fileMapper.deleteAll();
		bookService.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void 이미지_없는_상품_등록_테스트() throws Exception {
		assertThat(bookService.goodsEnroll(bookWithoutImage), is(1));
		assertThat(bookService.getCount(), is(1));
	}
	
	@Test
	public void 이미지_있는_상품_등록_테스트() throws Exception {
		assertThat(bookService.goodsEnroll(bookContainImage), is(2));
		
		int count = bookService.getCount();
		count += fileMapper.getCount();
		
		assertThat(count, is(2));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 이미지_있는_상품_등록_에러_테스트() throws Exception {
		bookService.goodsEnroll(bookContainWrongInfo);
	}
	
	@Test
	public void 상품_상세정보_테스트() throws Exception {
		bookService.goodsEnroll(bookContainImage);
		
		BookVO bookDetail = bookService.goodsDetail(bookService.getLastPK());
		
		assertThat(bookDetail.getBookName(), is(bookContainImage.getBookName()));
	}
	
	@Test
	public void 이미지_등록된_상품_삭제_테스트() throws Exception {
		bookService.goodsEnroll(bookContainImage);
		
		List<ImageInfoVO> list = bookService.goodsDelete(bookService.getLastPK());
		
		assertFalse(CollectionUtils.isEmpty(list));
		assertThat(bookService.getCount(), is(0));
	}
	
	@Test
	public void 이미지_없는_상품_삭제_테스트() throws Exception {
		bookService.goodsEnroll(bookWithoutImage);
		
		List<ImageInfoVO> list = bookService.goodsDelete(bookService.getLastPK());
		
		assertTrue(CollectionUtils.isEmpty(list));
		assertThat(bookService.getCount(), is(0));
	}
	
	@Test
	public void 이미지없는_상품_수정_테스트() throws Exception {
		bookService.goodsEnroll(bookWithoutImage);
		
		int lastBookId = bookService.getLastPK();
		
		bookWithoutImage.setBookId(lastBookId);
		bookWithoutImage.setBookIntro("상품정보수정");
		
		int updateResult = bookService.goodsModify(bookWithoutImage);
		
		BookVO updateDetail = bookService.goodsDetail(lastBookId);
		
		assertThat(updateResult, is(1));
		assertThat(updateDetail.getBookIntro(), is("상품정보수정"));
	}
	
	@Test
	public void 이미지있는_상품_수정_테스트() throws Exception {
		bookService.goodsEnroll(bookContainImage);
		
		int lastBookId = bookService.getLastPK();
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		imageList.add(replaceImageInfo);
		
		bookContainImage.setBookId(lastBookId);
		bookContainImage.setImagesList(imageList);
		
		int updateResult = bookService.goodsModify(bookContainImage);
		
		BookVO updateDetail = bookService.goodsDetail(lastBookId);
		
		ImageInfoVO updateImageInfo = fileMapper.getImageList(lastBookId).get(0);
		
		assertThat(updateResult, is(2));
		assertThat(updateDetail.getBookId(), is(bookContainImage.getBookId()));
		assertThat(updateImageInfo.getFileName(), is(replaceImageInfo.getFileName()));
	}
}
