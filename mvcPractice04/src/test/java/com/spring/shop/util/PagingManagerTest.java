package com.spring.shop.util;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class PagingManagerTest {

	private PageInfo pageInfo;
	
	@Before
	public void setUp() throws Exception {
		pageInfo = new PageInfo(3, 10);
	}
	
	@Test
	public void 페이징처리_테스트() throws Exception {
		int totalCount = 200; 
		
		PagingManger pagingManager = new PagingManger(pageInfo, totalCount);
		
		assertThat(false, is(pagingManager.isPrev()));
		assertThat(10, is(pagingManager.getPageEndNum()));
	}

}
