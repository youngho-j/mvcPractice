package com.spring.shop.vo;

import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookVOTest {

	@Test
	public void BookVO_테스트() {
		BookVO book = new BookVO();
		
		book.setBookId(1);
		book.setBookName("삼국지");
		
		assertNotNull(book);
		
		log.info(book.toString());
	}

}
