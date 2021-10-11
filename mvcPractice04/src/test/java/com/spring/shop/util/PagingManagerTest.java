package com.spring.shop.util;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PagingManagerTest {

	@Test
	public void 페이징처리_테스트() throws Exception{
		
		PagingManager paging = new PagingManager(4, 5);
		
		log.info(paging.toString());
		
		assertThat(15, is(paging.getSkip()));
	}

}
