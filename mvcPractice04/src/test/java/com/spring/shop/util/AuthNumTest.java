package com.spring.shop.util;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthNumTest {
	
	@Test
	public void 인증번호_자리수_테스트() throws Exception {
		AuthNumber authNumber = new AuthNumber();
		
		String authNum = String.valueOf(authNumber.getAuthNum());
		
		log.info("인증번호 : " + authNum);
		
		assertThat(6, is(authNum.length()));
	}

}
