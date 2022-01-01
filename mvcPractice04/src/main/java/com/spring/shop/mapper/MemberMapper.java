package com.spring.shop.mapper;

import com.spring.shop.vo.MemberVO;

public interface MemberMapper {
	
	// 회원가입
	public int memberJoin(MemberVO memberVO) throws Exception;
	
	// 아이디 중복 체크
	public boolean idCheck(String memberId) throws Exception;
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll() throws Exception;
	
	// 테이블 전체 삭제 검증(테스트용) 
	public int getCount() throws Exception;
}
