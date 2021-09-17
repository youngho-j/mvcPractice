package com.spring.shop.mapper;

import com.spring.shop.vo.MemberVO;

public interface MemberMapper {
	
	// 회원가입
	public int memberJoin(MemberVO memberVO) throws Exception;
}
