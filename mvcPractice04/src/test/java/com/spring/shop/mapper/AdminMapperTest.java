package com.spring.shop.mapper;

import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AdminMapperTest {
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Test
	public void 책_등록_테스트() throws Exception {
		BookVO book = new BookVO();
		
		book.setBookName("mapper 테스트");
		book.setAuthorId(123);
		book.setPublicationDate("2021-03-18");
		book.setPublisher("출판사");
		book.setCategoryCode("0231");
		book.setBookPrice(20000);
		book.setBookStock(300);
		book.setBookDiscount(0.23);
		book.setBookIntro("책 소개 ");
		book.setBookContents("책 목차 ");
		
		log.info(book.toString());
		
		int result = adminMapper.bookEnroll(book);
		
		assertThat(1, is(result));
	}
	
	@Test
	public void 카테코리목록_출력_테스트() throws Exception {
		List<CategoryVO> list = adminMapper.categoryList();
		
		assertNotNull(list);
		
		log.info(list.toString());
	} 
	
	@Test
	public void 상품리스트_출력_테스트() throws Exception {
		PageInfo pageInfo = new PageInfo(1, 10);
		
		List<BookVO> list = adminMapper.goodsList(pageInfo);
		
		assertThat(0, is(list.size()));
	}
	
	@Test
	public void 상품총개수_출력_테스트() throws Exception {
		PageInfo pageInfo = new PageInfo(1, 10);
		
		int result = adminMapper.goodsTotal(pageInfo);
		
		assertThat(0, is(result));
	}
	
	@Test
	public void 상품_상세조회_출력_테스트() throws Exception {
		BookVO detail = adminMapper.goodsDetail(8);
		
		log.info(detail.toString());
		
		assertThat(detail.getAuthorName(), is("신형만"));
	}
	
	@Test
	public void 상품_정보수정_테스트() throws Exception {
		BookVO modify = new BookVO();
		
		modify.setBookId(8);
		modify.setBookName("가즈아한국사");
		modify.setAuthorId(22);
		modify.setPublicationDate("2021-10-12");
		modify.setPublisher("한빛");
		modify.setCategoryCode("106003");
		modify.setBookPrice(30000);
		modify.setBookStock(15);
		modify.setBookDiscount(0.2);
		modify.setBookIntro("<p>테스트</p>");
		modify.setBookContents("<p>테스트</p>");
		
		int result = adminMapper.goodsModify(modify);
		
		assertThat(1, is(result));
	}
	
	@Test
	public void 상품_정보삭제_테스트() throws Exception {
		int result = adminMapper.goodsDelete(11);
		
		assertThat(1, is(result));
	}
	
	@Test
	public void 이미지_쩡보_등록_테스트() throws Exception {
		ImageInfoVO imageInfo = new ImageInfoVO
				.Builder()
				.bookId(8)
				.uploadPath("test")
				.uuid("test")
				.fileName("test")
				.build();
		
		int result = adminMapper.goodsImgEnroll(imageInfo);
		assertThat(1, is(result));
	}
	
	@Test
	public void 상품_이미지정보_삭제_테스트() throws Exception {
		int result = adminMapper.goodsImgDelete(8);
		
		assertThat(0, is(result));
	}
}
