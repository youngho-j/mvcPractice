package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.BookVO;

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

}
