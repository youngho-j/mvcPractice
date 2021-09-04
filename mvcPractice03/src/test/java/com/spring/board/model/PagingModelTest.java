package com.spring.board.model;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PagingModelTest {
	
	@Test
	public void 페이징_모델_테스트() throws Exception {
		PagingModel pagingModel = new PagingModel(1, 10);
		
		assertThat(10, is(pagingModel.getViewPerPage()));
		assertThat(1, is(pagingModel.getCurPageNum()));
		assertThat(0, is(pagingModel.getSkipOverPost()));
		
	}
}
