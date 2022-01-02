package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.MemberMapper;
import com.spring.shop.util.EncodePassword;
import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	private EncodePassword passwordEncoder;
	
	// 회원 가입
	@Override
	public int memberJoin(MemberVO memberVO) throws Exception {
		passwordEncoder = new EncodePassword(new BCryptPasswordEncoder());
		
		String encodePassword = "";
		
		log.info("비밀번호 암호화");
		
		encodePassword = passwordEncoder.EncodingPassword(memberVO.getMemberPw());
		
		memberVO.setMemberPw(encodePassword);
		
		return memberMapper.memberJoin(memberVO);
	}
	
	// 회원가입시 아이디 중복 체크
	@Override
	public boolean idCheck(String string) throws Exception {
		return memberMapper.idCheck(string);
	}

	@Override
	public void deleteAll() throws Exception {
		memberMapper.deleteAll();
	}

	@Override
	public int getCount() throws Exception {
		return memberMapper.getCount();
	}
	
	
}
