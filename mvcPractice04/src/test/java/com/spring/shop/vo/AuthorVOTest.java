package com.spring.shop.vo;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorVOTest {

	@Test
	public void AuthorVO_테스트() {
		AuthorVO authorVO = new AuthorVO();
		
		authorVO.setAuthorId(1);
		authorVO.setAuthorName("라이언");
		authorVO.setNationId("01");
		
		assertNotNull(authorVO);
		assertThat("국내", is(authorVO.getNationName()));
		
		log.info(authorVO.toString());
	}

}