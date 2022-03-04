package com.spring.myApp.enums;

public enum ExcelFontSize {
	EIGHT((short) 8),
	NINE((short) 9),
	TEN((short) 10),
	TWELVE((short) 12),
	FIFTEEN((short) 15),
	TWENTY((short) 20);
	
	private final short value;
	
	ExcelFontSize(short value) {
		this.value = value;
	}
	
	public short getValue() {return value;}
}
