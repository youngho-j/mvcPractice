package com.spring.shop.vo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberVOTest {
	
	private MemberVO memberVO;
	
	@Before
	public void setUp() {
		memberVO = new MemberVO();
		
		memberVO.setMemberId("testId");
		memberVO.setMemberPw("1q2w3e4r");
	}
	
	@Test
	public void MemberVO_getter_테스트() {
		assertEquals("testId", memberVO.getMemberId());
		
		log.info("getter return : " + memberVO.getMemberId());
	}
}
