package com.spring.shop.vo;

import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryVOTest {

	@Test
	public void categoryVO_테스트() {
		CategoryVO category = new CategoryVO();
		
		category.setTier(1);
		category.setCategoryName("국내");
		category.setCategoryCode("100000");
		
		assertNotNull(category);
		
		log.info(category.toString());
	}

}
