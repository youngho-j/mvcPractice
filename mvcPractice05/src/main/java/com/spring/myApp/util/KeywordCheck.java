package com.spring.myApp.util;

public class KeywordCheck {

	public KeywordCheck() {};
	
	public String convertKeyword(String str) {
		
		if(str == null || str.trim().isEmpty()) {
			return "올림픽";			
		}
		
		return str.trim().replaceAll("\\s+","+");
	}
	
	public String restoreKeyword(String str) {
		if(str.contains("+")) {
			return str.replaceAll("\\++", " ");
		}
		return str;
	}
}
