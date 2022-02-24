package com.spring.myApp.util;

public class KeywordCheck implements ValueCheck{

	public KeywordCheck() {}
	
	@Override
	public String convertValue(String str) {
		
		if(str == null || str.trim().isEmpty()) {
			return "올림픽";			
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
