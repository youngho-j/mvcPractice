package com.spring.shop.mapper;

import com.spring.shop.vo.CustomUserDetails;

public interface AuthMapper {
	
	// 로그인 정보
	public CustomUserDetails getUserById(String userName);
	
}
