package com.spring.board.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class CommonDtoTest {

	@Test
	public void 롬복_기능_테스트() throws Exception {
		CommonDto dto = new CommonDto();
		
		dto.setPagination("성공");
		dto.setLimit(5);
		
		assertThat(dto.getPagination()).isEqualTo("성공");
		assertThat(dto.getLimit()).isEqualTo(5);
		
	}

}
