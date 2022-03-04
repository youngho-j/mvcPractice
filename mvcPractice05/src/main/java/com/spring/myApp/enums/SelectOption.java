package com.spring.myApp.enums;

public enum SelectOption {
	LATEST("1"),
	RELATED("0");
	
	private final String receivedValue;
	private final String searchOption;
	
	SelectOption(String receiveValue) {
		this.receivedValue = receiveValue;
		if(receiveValue == "1") {
			this.searchOption = "최신";				
		} else {
			this.searchOption = "관련";								
		}
	}
	
	public static SelectOption OptionCheck(String str) {
		if(!str.equals("0") && !str.equals("1")) {
			throw new IllegalArgumentException("Invalid value :" + str);
		} 
		
		if(str.equals("0")) {
			return SelectOption.RELATED;				
		}
		
		return SelectOption.LATEST;
	}
	
	public String getSearchOption() {
		return searchOption;
	}

	public String getReceiveValue() {
		return receivedValue;
	}
}
