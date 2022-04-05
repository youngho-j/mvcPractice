package com.spring.shop.vo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		assertEquals(memberVO.getMemberId(), "testId");
	}
}
