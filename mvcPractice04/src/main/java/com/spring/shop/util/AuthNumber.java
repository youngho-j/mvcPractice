package com.spring.shop.util;

import java.util.Random;

public class AuthNumber {
	
	private Random generator;
	
	public AuthNumber() {
		generator = new Random();
	}
	
	// 인증번호 생성 - 시간 활용하여 무작위 생성
	public int getAuthNum() throws Exception {
		generator.setSeed(System.currentTimeMillis());
		return generator.nextInt(1000000) % 1000000;
	}
}
