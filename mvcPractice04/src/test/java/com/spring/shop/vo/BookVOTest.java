package com.spring.shop.vo;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class BookVOTest {

	@Test
	public void BookVO_테스트() {
		BookVO book = new BookVO();
		
		book.setBookId(1);
		book.setBookName("삼국지");
		
		assertNotNull(book);
		assertThat(book.getBookName(), is("삼국지"));
	}

}
