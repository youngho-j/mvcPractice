package com.spring.shop.service;

import com.spring.shop.vo.MemberVO;

public interface MemberService {
	
	// 회원 가입
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	// 아이디 중복 체크
	public boolean idCheck(String string) throws Exception;
	
	// 테이블 내 정보 삭제(테스트 용)
	public void deleteAll() throws Exception;
	
	// 테이블 내 정보 삭제 검증(테스트 용)
	public int getCount() throws Exception;
}
