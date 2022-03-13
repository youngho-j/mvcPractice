package com.spring.myApp.util;

import com.spring.myApp.enums.SearchTerms;

public class KeywordCheck implements ValueCheck {

	public KeywordCheck() {}
	
	@Override
	public String convertValue(String str) {
		
		if(str == null || str.trim().isEmpty()) {
			str = SearchTerms.War.getKeyword();		
		}
		
		return str.trim().replaceAll("\\s+","+");
	}
	
	@Override
	public String restoreValue(String str) {
		if(str.contains("+")) {
			return str.replaceAll("\\++", " ");
		}
		return str;
	}
}
