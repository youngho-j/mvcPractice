package com.spring.shop.service;

import static org.junit.Assert.*;

import org.junit.Before;

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
	
	private MemberVO testInfo1;
	
	private EncodePassword passwordEncoder;
	
	@Before
	public void setUp() {
		passwordEncoder = new EncodePassword(new BCryptPasswordEncoder());
		
		testInfo1 = new MemberVO();
		
		testInfo1.setMemberId("test1");
		testInfo1.setMemberPw("1q2w3e4r");
		testInfo1.setMemberName("test");		
		testInfo1.setMemberMail("test");		
		testInfo1.setMemberAddr1("test");	
		testInfo1.setMemberAddr2("test");	
		testInfo1.setMemberAddr3("test");
	}
	
	@Test
	public void 회원등록_테스트() throws Exception {
		
		memberService.deleteAll();
		
		assertThat(memberService.getCount(), is(0));
		
		String encoding = passwordEncoder.EncodingPassword(testInfo1.getMemberPw());
		
		assertTrue(passwordEncoder.comparePassword(testInfo1.getMemberPw(), encoding));
		
		assertThat(1, is(memberService.memberJoin(testInfo1)));
		
	}
	
	@Test
	public void 아이디중복체크_테스트() throws Exception {
		
		memberService.deleteAll();
		
		assertThat(memberService.getCount(), is(0));
		
		memberService.memberJoin(testInfo1);
		
		assertFalse(memberService.idCheck("test232"));
		assertTrue(memberService.idCheck(testInfo1.getMemberId()));
	}
}
