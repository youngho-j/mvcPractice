package com.spring.board.dto;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.board.model.PagingModel;

public class PageDataDTOTest {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private PagingModel pagingModel;
	
	@Before
	public void setUp() throws Exception {
		pagingModel = new PagingModel();
	}
	
	@Test
	public void PageDataDTO_테스트() throws Exception {
		PageDataDTO pageDataDTO = new PageDataDTO(pagingModel, 13);
		
		assertThat(2, is(pageDataDTO.getEndPage()));
		
		logger.info(pageDataDTO.toString());
	}
}
