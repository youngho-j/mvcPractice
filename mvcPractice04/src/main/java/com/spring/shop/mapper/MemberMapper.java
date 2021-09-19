package com.spring.shop.mapper;

import com.spring.shop.vo.MemberVO;

public interface MemberMapper {
	
	// 회원가입
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	// 아이디 중복 체크
	public int idCheck(String memberId) throws Exception;
}
