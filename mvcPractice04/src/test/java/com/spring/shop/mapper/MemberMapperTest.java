package com.spring.shop.mapper;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	private static MemberVO testUserInfo1;
	private static MemberVO testUserInfo2;
	
	@BeforeClass
	public static void setUp() {
		testUserInfo1 = new MemberVO();
		
		testUserInfo2 = new MemberVO();
		
		testUserInfo1.setMemberId("testID1");
		testUserInfo1.setMemberPw("1q2w3e4r");
		testUserInfo1.setMemberName("test");		
		testUserInfo1.setMemberMail("test");		
		testUserInfo1.setMemberAddr1("test");	
		testUserInfo1.setMemberAddr2("test");	
		testUserInfo1.setMemberAddr3("test");
		
		testUserInfo2.setMemberId("testID2");
		testUserInfo2.setMemberPw("1q2w3e4r");
		testUserInfo2.setMemberName("test1");		
		testUserInfo2.setMemberMail("test1");		
		testUserInfo2.setMemberAddr1("test1");	
		testUserInfo2.setMemberAddr2("test1");	
		testUserInfo2.setMemberAddr3("test1");
	}
	
	@Before
	public void beforeMethod() throws Exception {
		memberMapper.deleteAll();
		assertThat(memberMapper.getCount(), is(0));
	}
	
	@After
	public void afterMethod() throws Exception {
		memberMapper.deleteAll();
		assertThat(memberMapper.getCount(), is(0));
	}
	
	@Test
	public void 회원가입_메서드_테스트() throws Exception {
		// 1. 회원가입을 위한 정보 입력
		memberMapper.memberJoin(testUserInfo1);
		
		// 2. 회원정보가 저장되었는지 확인 - getCount 사용하여 저장된 회원정보가 1개 출력되어야함
		assertThat(memberMapper.getCount(), is(1));
		
		// 3. 회원가입 정보 삭제 - 기존 상태로 복구
		memberMapper.deleteAll();
		
		// 4. 정보 삭제 완료 확인(getCount);
		assertThat(memberMapper.getCount(), is(0));
		
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		// 조건1. 테이블에 회원정보가 1명 존재시, 예상결과 1
		memberMapper.memberJoin(testUserInfo1);
		assertThat(memberMapper.getCount(), is(1));
		
		// 조건2. 테이블에 회원정보가 2명 존재시, 예상결과 2
		memberMapper.memberJoin(testUserInfo2);
		assertThat(memberMapper.getCount(), is(2));
	}

	@Test
	public void 아이디중복체크_메서드_테스트() throws Exception {
		// 조건1. 아이디 검색을 위해 회원 등록
		memberMapper.memberJoin(testUserInfo1);
		
		// 결과1. 회원 정보가 존재하는 경우
		assertTrue(memberMapper.idCheck(testUserInfo1.getMemberId()));
		
		// 결과2. 회원 정보가 존재하지 않는 경우
		assertFalse(memberMapper.idCheck(testUserInfo2.getMemberId()));
		
	}
}
