package com.spring.myApp.util;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SelectOptionCheckTest {
	
	private SelectOptionCheck check;
	
	@Before
	public void setUp() {
		check = new SelectOptionCheck();
	}
	
	@Test
	public void 옵션이_빈값일_경우_변환_메서드_테스트() throws Exception {
		
		String str = "  ";
			
		String result = check.convertValue(str);
		
		assertThat(result, is("1"));
	}
	
	@Test
	public void 옵션이_null일_경우_변환_메서드_테스트() throws Exception {
		
		String str = null;
		
		String result = check.convertValue(str);
		
		assertThat(result, is("1"));
	}
	
	@Test
	public void 선택된_값일_경우_변환_메서드_테스트() throws Exception {
		
		String str = "2";
		
		String result = check.convertValue(str);
		
		assertThat(result, is("2"));
	}
}
