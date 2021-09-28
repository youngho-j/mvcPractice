package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.MemberMapper;
import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	private EncodePassword passwordEncoder;
	
	// 회원 가입
	@Override
	public int memberJoin(MemberVO memberVO) throws Exception {
		
		String encodePassword = "";
		
		log.info("비밀번호 암호화");
		
		encodePassword = passwordEncoder.EncodingPassword(memberVO.getMemberPw());
		
		memberVO.setMemberPw(encodePassword);
		
		return memberMapper.memberJoin(memberVO);
	}

	@Override
	public int idCheck(String string) throws Exception {
		return memberMapper.idCheck(string);
	}

	@Override
	public MemberVO memberLogin(MemberVO memberVO) throws Exception {
		return memberMapper.memberLogin(memberVO);
	}
	
	
}
