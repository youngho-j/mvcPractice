package com.spring.board.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class PagingUtilTest {
	
	private CommonForm commonForm; 
	
	@Before
	public void setUp() throws Exception {
		commonForm = new CommonForm();
		commonForm.setFunction_name("출력");
		commonForm.setCurrent_page_num(4);
		commonForm.setCount_per_list(3);
		commonForm.setCount_per_page(5);
		commonForm.setTotal_list_count(29);
	}
	
	@Test
	public void 페이징_유틸_테스트() throws Exception {
		CommonDto dto = PagingUtil.setPageUtil(commonForm);
		
		assertThat(dto.getLimit()).isEqualTo(3);
		assertThat(dto.getOffset()).isEqualTo(9);
	}

}
