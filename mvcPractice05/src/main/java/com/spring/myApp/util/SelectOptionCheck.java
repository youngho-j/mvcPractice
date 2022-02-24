package com.spring.myApp.util;

public class SelectOptionCheck implements ValueCheck {
	
	public SelectOptionCheck() {}
	
	@Override
	public String convertValue(String str) {
		if(str == null || str.trim().isEmpty()) {
			return "1";			
		}
		return str;
	}
}
