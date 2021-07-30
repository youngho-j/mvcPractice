package com.spring.board.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CommonFormTest {


	@Test
	public void 롬복_기능테스트() throws Exception {
		CommonForm form = new CommonForm("함수", 1, 5, 5, 25, 5, 5, 1);
		
		assertThat(form.getFuntion_name()).isEqualTo("함수");
		assertThat(form.getTotal_list_count()).isEqualTo(25);
	}

}
