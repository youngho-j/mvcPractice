package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	@Test
	public void 회원등록_테스트() throws Exception {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("testID2");
		memberVO.setMemberPw("1q2w3e4r");
		memberVO.setMemberName("test");		
		memberVO.setMemberMail("test");		
		memberVO.setMemberAddr1("test");	
		memberVO.setMemberAddr2("test");	
		memberVO.setMemberAddr3("test");
		
		log.info(memberVO.toString());
		
		assertThat(1, is(memberService.memberJoin(memberVO)));
	}

}
