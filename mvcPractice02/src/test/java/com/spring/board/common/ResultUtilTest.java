package com.spring.board.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import com.spring.board.dto.BoardDto;

public class ResultUtilTest {
	
	private ResultUtil resultUtil;
	private BoardDto dto;
	
	@Before
	public void setUp() throws Exception {
		resultUtil = new ResultUtil();
		dto = new BoardDto();
		dto.setResult("SUCCESS");
		resultUtil.setState("SUCCESS");
		resultUtil.setMsg("성공");
		resultUtil.setData(dto);;
	}
	
	@Test
	public void 롬복_기능테스트() throws Exception {
		assertThat(resultUtil.getState()).isEqualTo("SUCCESS");
		BoardDto result = (BoardDto)resultUtil.getData();
		assertThat(result.getResult()).isEqualTo("SUCCESS");
	}
}
