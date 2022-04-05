package com.spring.shop.vo;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class CategoryVOTest {

	@Test
	public void categoryVO_테스트() {
		CategoryVO category = new CategoryVO();
		
		category.setTier(1);
		category.setCategoryName("국내");
		category.setCategoryCode("100000");
		
		assertNotNull(category);
		assertThat(category.getCategoryName(), is("국내"));
	}

}
