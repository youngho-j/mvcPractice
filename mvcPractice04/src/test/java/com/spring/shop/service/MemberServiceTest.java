package com.spring.shop.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.EncodePassword;
import com.spring.shop.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class MemberServiceTest {

	@Autowired
	private MemberService memberService;
	
	private static MemberVO testUserInfo;
	
	private static EncodePassword passwordEncoder;
	
	@BeforeClass
	public static void setUp() {
		passwordEncoder = new EncodePassword(new BCryptPasswordEncoder());
		
		testUserInfo = new MemberVO();
		
		testUserInfo.setMemberId("test1");
		testUserInfo.setMemberPw("1q2w3e4r");
		testUserInfo.setMemberName("test");		
		testUserInfo.setMemberMail("test");		
		testUserInfo.setMemberAddr1("test");	
		testUserInfo.setMemberAddr2("test");	
		testUserInfo.setMemberAddr3("test");
	}
	
	@Before
	public void beforeMethod() throws Exception {
		memberService.deleteAll();
		
		assertThat(memberService.getCount(), is(0));		
	}
	
	@After
	public void afterMethod() throws Exception {
		memberService.deleteAll();
		
		assertThat(memberService.getCount(), is(0));		
	}
	
	@Test
	public void 회원등록_테스트() throws Exception {
		String encoding = passwordEncoder.EncodingPassword(testUserInfo.getMemberPw());
		
		assertTrue(passwordEncoder.comparePassword(testUserInfo.getMemberPw(), encoding));
		
		assertThat(1, is(memberService.memberJoin(testUserInfo)));
		
	}
	
	@Test
	public void 아이디중복체크_테스트() throws Exception {
		memberService.memberJoin(testUserInfo);
		
		assertFalse(memberService.idCheck("test232"));
		
		assertTrue(memberService.idCheck(testUserInfo.getMemberId()));
	}
}
