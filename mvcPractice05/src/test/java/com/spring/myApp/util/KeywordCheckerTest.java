package com.spring.myApp.util;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KeywordCheckerTest {
	
	private KeywordCheck check;
	
	@Before
	public void setUp() {
		check = new KeywordCheck();
	}
	
	@Test
	public void 앞뒤_공백이_존재하는_검색어_변환_메서드_테스트() throws Exception {
		
		String str = " sad  2352 ";
			
		String result = check.convertKeyword(str);
		
		assertThat(result, is("sad+2352"));
	}
	
	@Test
	public void 띄어쓰기가_긴_검색어_변환_메서드_테스트() throws Exception {
		
		String str = "hi,          hello";
		
		String result = check.convertKeyword(str);
		
		assertThat(result, is("hi,+hello"));
	}
	
	@Test
	public void 공백_검색어_변환_메서드_테스트() throws Exception {
		
		String str = "  ";
		
		String result = check.convertKeyword(str);
		
		assertThat(result, is("올림픽"));
	}
	
	@Test
	public void null값_검색어_변환_메서드_테스트() throws Exception {
		
		String str = null;
		
		String result = check.convertKeyword(str);
		
		assertThat(result, is("올림픽"));
	}
	
	@Test
	public void 변환된_검색어_복원_메서드_테스트() throws Exception {
		
		String str = "test+code";
		
		String result = check.restoreKeyword(str);
		
		assertThat(result, is("test code"));
	}
	
	@Test
	public void 변환되지_않은_검색어_복원_메서드_테스트() throws Exception {
		
		String str = "testCode";
		
		String result = check.restoreKeyword(str);
		
		assertThat(result, is("testCode"));
	}

}
