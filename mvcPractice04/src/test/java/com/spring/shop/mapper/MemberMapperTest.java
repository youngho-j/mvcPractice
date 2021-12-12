package com.spring.shop.mapper;

import static org.junit.Assert.*;

import org.junit.Before;

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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	private MemberVO testInfo1;
	private MemberVO testInfo2;
	
	@Before
	public void setUp() {
		testInfo1 = new MemberVO();
		
		testInfo2 = new MemberVO();
		
		testInfo1.setMemberId("testID");
		testInfo1.setMemberPw("1q2w3e4r");
		testInfo1.setMemberName("test");		
		testInfo1.setMemberMail("test");		
		testInfo1.setMemberAddr1("test");	
		testInfo1.setMemberAddr2("test");	
		testInfo1.setMemberAddr3("test");
		
		testInfo2.setMemberId("testID2");
		testInfo2.setMemberPw("1q2w3e4r");
		testInfo2.setMemberName("test1");		
		testInfo2.setMemberMail("test1");		
		testInfo2.setMemberAddr1("test1");	
		testInfo2.setMemberAddr2("test1");	
		testInfo2.setMemberAddr3("test1");
	}
	
	@Test
	public void 회원가입_메서드_테스트() throws Exception {
		
		// 테이블이 비어있어야됨(회원가입이 되었는지 쉽게 확인을 위해)
		memberMapper.deleteAll();
		
		// 테이블이 빈것을 검증할 수 있어야함 - 위 전체삭제 메서드 검증을 위해 해당 메서드 사용, 예상 결과 0
		assertThat(memberMapper.getCount(), is(0));
		
		// 1. 회원가입을 위한 정보 입력
		memberMapper.memberJoin(testInfo1);
		
		// 2. 회원정보가 저장되었는지 확인 - getCount 사용하여 저장된 회원정보가 1개 출력되어야함
		assertThat(memberMapper.getCount(), is(1));
		
		// 3. 회원가입 정보 삭제 - 기존 상태로 복구
		memberMapper.deleteAll();
		
		// 4. 정보 삭제 완료 확인(getCount);
		assertThat(memberMapper.getCount(), is(0));
		
	}
	
	@Test
	public void getCount_메서드_테스트() throws Exception {
		// getCount 쿼리문 동작 테스트 
		// 조건1. 테이블에 회원정보가 없을 경우, 예샹결과 0 
		memberMapper.deleteAll();
		assertThat(memberMapper.getCount(), is(0));
		
		// 조건2. 테이블에 회원정보가 1명 존재시, 예상결과 1
		memberMapper.memberJoin(testInfo1);
		assertThat(memberMapper.getCount(), is(1));
		
		// 조건3. 테이블에 회원정보가 2명 존재시, 예상결과 2
		memberMapper.memberJoin(testInfo2);
		assertThat(memberMapper.getCount(), is(2));
		
		// 테이블 데이터 초기화
		memberMapper.deleteAll();
	}

	@Test
	public void 아이디중복체크_메서드_테스트() throws Exception {
		assertThat(0,  is(memberMapper.idCheck("wndqhr1")));
	}
	
	@Test
	public void 로그인_메서드_테스트() throws Exception {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemberId("admin");
		MemberVO result = memberMapper.memberLogin(memberVO);
		assertNotNull(result);
		
		log.info(result.getMemberPw());
	}
}
