package com.spring.shop.mapper;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	
	private static BookVO bookWithoutAuthorId;
	
	private static AuthorVO authorInfo;
	
	private static ImageInfoVO imageInfoSavedToday;
	private static ImageInfoVO imageInfoWithWrongBookId;
	private static ImageInfoVO imageInfoSavedDayBefore;
	
	@BeforeClass
	public static void setUp() throws Exception {
		
		String today = LocalDate.now()
				.format(DateTimeFormatter.ofPattern("yyyy\\MM\\dd"));
		
		String dayBefore = LocalDate.now().minusDays(1)
				.format(DateTimeFormatter.ofPattern("yyyy\\MM\\dd"));
		
		authorInfo = new AuthorVO();
		
		authorInfo.setNationId("01");
		authorInfo.setAuthorName("테스트1");
		authorInfo.setAuthorProfile("테스터입니다.");
		
		bookWithoutAuthorId = new BookVO();
		
		bookWithoutAuthorId.setBookName("테스트책");
		bookWithoutAuthorId.setPublicationDate("2022-01-10");
		bookWithoutAuthorId.setPublisher("한국출판사");
		bookWithoutAuthorId.setCategoryCode("104000");
		bookWithoutAuthorId.setBookPrice(20000);
		bookWithoutAuthorId.setBookStock(50);
		bookWithoutAuthorId.setBookDiscount(0.2);
		bookWithoutAuthorId.setBookIntro("책 소개 ");
		bookWithoutAuthorId.setBookContents("책 목차 ");
		
		imageInfoSavedToday = new ImageInfoVO
				.Builder()
				.uploadPath("test\\".concat(today))
				.uuid("test")
				.fileName("test").build();
		
		imageInfoWithWrongBookId = new ImageInfoVO
				.Builder()
				.bookId(100)
				.uploadPath("test2")
				.uuid("test2")
				.fileName("test2").build();
		
		imageInfoSavedDayBefore = new ImageInfoVO
				.Builder()
				.uploadPath("test\\".concat(dayBefore))
				.uuid("test3")
				.fileName("test3").build();
	}
	
	@Before
	public void beforeMethod() throws Exception {
		authorMapper.authorEnroll(authorInfo);
		
		bookWithoutAuthorId.setAuthorId(authorMapper.getLastPK());
		
		bookMapper.bookEnroll(bookWithoutAuthorId);
		
		int bookId = bookMapper.getLastPK();
		
		imageInfoSavedToday.setBookId(bookId);
		imageInfoSavedDayBefore.setBookId(bookId);
	}
	
	@After
	public void afterMethod() {
		fileMapper.deleteAll();
		bookMapper.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		fileMapper.goodsImgEnroll(imageInfoSavedToday);
		assertThat(fileMapper.getCount(), is(1));
	}
	
	@Test(expected = DataIntegrityViolationException.class)
	public void 이미지_정보_등록_외래키_테스트() throws Exception {
		
		int result = fileMapper.goodsImgEnroll(imageInfoWithWrongBookId);
		
		assertThat(result, is(0));
		
		assertThat(fileMapper.getCount(), is(0));
	}
	
	@Test
	public void 이미지_정보_등록_테스트() throws Exception {
		int result = fileMapper.goodsImgEnroll(imageInfoSavedToday);
		
		assertThat(result, is(1));
		
		assertThat(fileMapper.getCount(), is(1));
	}
	
	@Test
	public void 이미지_목록_출력_테스트() throws Exception {
		
		fileMapper.goodsImgEnroll(imageInfoSavedToday);
		fileMapper.goodsImgEnroll(imageInfoSavedDayBefore);
		
		List<ImageInfoVO> list = fileMapper.getImageList(fileMapper.getLastPK());
		
		assertThat(list.size(), is(2));
		assertThat(list.get(0).getUploadPath(), is(imageInfoSavedToday.getUploadPath()));
	}
	
	@Test
	public void 하루전_이미지_목록_출력_테스트() throws Exception {
		fileMapper.goodsImgEnroll(imageInfoSavedToday);
		fileMapper.goodsImgEnroll(imageInfoSavedDayBefore);
		
		List<ImageInfoVO> list = fileMapper.getPreviousImageList();
		
		assertThat(list.size(), is(1));
		assertThat(list.get(0).getUploadPath(), is(imageInfoSavedDayBefore.getUploadPath()));
	}
	
	
}
