package com.spring.shop.mapper;

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
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	public void 회원가입_메서드_테스트() throws Exception {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("testID");
		memberVO.setMemberPw("1q2w3e4r");
		memberVO.setMemberName("test");		
		memberVO.setMemberMail("test");		
		memberVO.setMemberAddr1("test");	
		memberVO.setMemberAddr2("test");	
		memberVO.setMemberAddr3("test");
		
		log.info(memberVO.toString());
		
		int result = memberMapper.memberJoin(memberVO);
		
		assertThat(1, is(result));
	}

	@Test
	public void 아이디중복체크_메서드_테스트() throws Exception {
		assertThat(0,  is(memberMapper.idCheck("wndqhr1")));
	}
	
	@Test
	public void 로그인_메서드_테스트() throws Exception {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("admin");
		memberVO.setMemberPw("admin");
		
		assertNotNull(memberMapper.memberLogin(memberVO));
	}
}
