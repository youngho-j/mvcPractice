package com.spring.shop.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePassword {
	
	private final BCryptPasswordEncoder passwordEncoder;
	
	public EncodePassword(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public String EncodingPassword(String password) throws Exception {
		
		String encodePassword = "";
		
		encodePassword = passwordEncoder.encode(password);
		
		return encodePassword;
	}

	public boolean comparePassword(String password, String encoding) {
		if(passwordEncoder.matches(password, encoding)) {
			return true;			
		}
		return false;
	}
}
