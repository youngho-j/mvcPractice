package com.spring.myApp.enums;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectOptionEnumTest {
	
	Logger log = LoggerFactory.getLogger(SelectOption.class);
	
	@Test
	public void selectOption_enum값_조회1() throws Exception {
		Map<String, Object> map = new HashedMap<String, Object>();
		
		map.put("selectOption", "1");
		
		Object obj = map.get("selectOption");
		
		SelectOption option = SelectOption
				.OptionCheck(obj.toString());
		
		String receivedValue = option.getReceiveValue();
		String searchValue = option.getSearchOption();
		
		log.info(receivedValue);
		
		assertThat(receivedValue, is("1"));
		assertThat(searchValue, is("최신"));
	}
	
	@Test
	public void selectOption_enum값_조회() throws Exception {
		SelectOption option = SelectOption
				.OptionCheck("1");
		
		String receivedValue = option.getReceiveValue();
		String searchValue = option.getSearchOption();
		
		assertThat(receivedValue, is("1"));
		assertThat(searchValue, is("최신"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void selectOption_enum값_오류발생() throws Exception {
		SelectOption option = SelectOption
				.OptionCheck("2");
		
		String receivedValue = option.getReceiveValue();
		
		assertThat(receivedValue, is("1"));
	}

}
