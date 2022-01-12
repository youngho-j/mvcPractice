package com.spring.shop.mapper;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.util.List;

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
import com.spring.shop.vo.ImageInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class FileMapperTest {
	
	@Autowired
	private FileMapper fileMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private BookVO testBook;
	
	private AuthorVO testAuthor;
	
	private ImageInfoVO testImage1;
	private ImageInfoVO testImage2;
	private ImageInfoVO testImage3;
	
	private int testBookId;
	
	@Before
	public void setUp() throws Exception {
		
		fileMapper.deleteAll();
		bookMapper.deleteAll();
		authorMapper.deleteAll();
		
		testAuthor = new AuthorVO();
		
		testAuthor.setNationId("01");
		testAuthor.setAuthorName("테스트1");
		testAuthor.setAuthorProfile("테스터입니다.");
		
		authorMapper.authorEnroll(testAuthor);
		
		int testAuthorId = authorMapper.getLastPK();
		
		testBook = new BookVO();
		
		testBook.setBookName("테스트책");
		testBook.setAuthorId(testAuthorId);
		testBook.setPublicationDate("2022-01-10");
		testBook.setPublisher("한국출판사");
		testBook.setCategoryCode("104000");
		testBook.setBookPrice(20000);
		testBook.setBookStock(50);
		testBook.setBookDiscount(0.2);
		testBook.setBookIntro("책 소개 ");
		testBook.setBookContents("책 목차 ");
		
		bookMapper.bookEnroll(testBook);
		
		testBookId = bookMapper.getLastPK();
		
		testImage1 = new ImageInfoVO
				.Builder()
				.bookId(testBookId)
				.uploadPath("test\\2022\\01\\12")
				.uuid("test")
				.fileName("test").build();
		
		testImage2 = new ImageInfoVO
				.Builder()
				.bookId(100)
				.uploadPath("test2")
				.uuid("test2")
				.fileName("test2").build();
		
		testImage3 = new ImageInfoVO
				.Builder()
				.bookId(testBookId)
				.uploadPath("test\\2022\\01\\11")
				.uuid("test3")
				.fileName("test3").build();
	}
	
	@After
	public void afterTest() {
		fileMapper.deleteAll();
		bookMapper.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		fileMapper.deleteAll();
		assertThat(fileMapper.getCount(), is(0));
		
		fileMapper.goodsImgEnroll(testImage1);
		assertThat(fileMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 이미지_정보_등록_외래키_테스트() throws Exception {
		
		int result = fileMapper.goodsImgEnroll(testImage2);
		
		assertThat(result, is(0));
		
		assertThat(fileMapper.getCount(), is(0));
	}
	
	@Test
	public void 이미지_정보_등록_테스트() throws Exception {
		
		int result = fileMapper.goodsImgEnroll(testImage1);
		
		assertThat(result, is(1));
		
		assertThat(fileMapper.getCount(), is(1));
	}
	
	@Test
	public void 이미지_목록_출력_테스트() throws Exception {
		
		fileMapper.goodsImgEnroll(testImage1);
		fileMapper.goodsImgEnroll(testImage3);
		
		List<ImageInfoVO> list = fileMapper.getImageList(fileMapper.getLastPK());
		
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getUploadPath(), is(testImage1.getUploadPath()));
	}
	
	@Test
	public void 하루전_이미지_목록_출력_테스트() throws Exception {
		fileMapper.goodsImgEnroll(testImage1);
		fileMapper.goodsImgEnroll(testImage3);
		
		List<ImageInfoVO> list = fileMapper.getPreviousImageList();
		
		assertThat(list.size(), is(1));
		assertThat(list.get(0).getUploadPath(), is(testImage3.getUploadPath()));
	}
	
	
}
