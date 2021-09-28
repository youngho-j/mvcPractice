package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodePassword {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
