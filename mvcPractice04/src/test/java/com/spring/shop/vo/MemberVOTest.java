package com.spring.shop.vo;

import static org.junit.Assert.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberVOTest {

	@Test
	public void MemberVO_테스트() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("testId");
		memberVO.setMemberPw("1q2w3e4r");
		
		assertNotNull(memberVO);
		
		log.info(memberVO.toString());
	}

}
