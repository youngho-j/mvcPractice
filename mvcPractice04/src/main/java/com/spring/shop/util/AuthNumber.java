package com.spring.shop.util;

import java.util.Random;

public class AuthNumber {
	
	private Random generator;
	
	public AuthNumber() {
		generator = new Random();
	}
	
	// 인증번호 생성 - 시간 활용하여 무작위 생성
	public String getAuthNum(int length) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		generator.setSeed(System.currentTimeMillis());
		
		for(int i = 0 ; i < length ; i++) {
			sb.append(generator.nextInt(10));
		}
		
		return sb.toString();
	}
}
