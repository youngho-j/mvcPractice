package com.spring.board.model;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

public class PagingModelTest {
	
	@Test
	public void 페이징_모델_테스트() throws Exception {
		PagingModel pagingModel = new PagingModel(1, 10);
		
		pagingModel.setKeyword("테스트");
		pagingModel.setType("TC");
		
		assertThat(10, is(pagingModel.getViewPerPage()));
		assertThat(1, is(pagingModel.getCurPageNum()));
		assertThat(0, is(pagingModel.getSkipOverPost()));
		assertThat("테스트", is(pagingModel.getKeyword()));
		assertArrayEquals(new String[]{"T","C"}, pagingModel.getTypeArr());
		
	}
}
