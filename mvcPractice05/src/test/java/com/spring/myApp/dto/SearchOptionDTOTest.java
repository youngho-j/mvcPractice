package com.spring.myApp.dto;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class SearchOptionDTOTest {

	@Test
	public void DTO_테스트() {
		String selectOption = "1";
		String keyword = "올림픽";
		
		SearchOptionDTO option 
			= new SearchOptionDTO(selectOption, keyword);
		
		assertThat(option.getSelectOption(), is(selectOption));
		assertThat(option.getKeyword(), is(keyword));
	}

}
