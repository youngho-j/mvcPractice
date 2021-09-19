package com.spring.shop.service;

import com.spring.shop.vo.MemberVO;

public interface MemberService {
	
	// 회원 가입
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	// 아이디 중복 체크
	public int idCheck(String string) throws Exception;
}
