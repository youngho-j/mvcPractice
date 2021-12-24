package com.spring.shop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	// 회원 id
	private String memberId;
		
	// 회원 비밀번호
	private String memberPw;
	
	// 회원 이름
	private String memberName;
		
	// 회원 이메일
	private String memberMail;
		
	// 회원 우편번호
	private String memberAddr1;
		
	// 회원 주소
	private String memberAddr2;
		
	// 회원 상세주소
	private String memberAddr3;
		 
	// 등록일자
	private int regDate;
		
	// 회원 돈
	private int money;
		
	// 회원 포인트
	private int point;
	
	// 인증
	private String auth;
	
	// 계정 활성화
	private int enabled;
}
