package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.shop.mapper.AuthMapper;
import com.spring.shop.vo.CustomUserDetails;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AuthMapper AuthMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CustomUserDetails user = AuthMapper.getUserById(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(username);
		}
		
		return user;
	}
	
}
