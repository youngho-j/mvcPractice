package com.spring.myApp.util;

public interface ValueCheck {
	String convertValue(String str);
	
	default String restoreValue(String str) {
		return str;
	}
}
