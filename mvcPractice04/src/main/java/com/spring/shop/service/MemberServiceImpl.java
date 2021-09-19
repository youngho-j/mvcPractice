package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.MemberMapper;
import com.spring.shop.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	// 회원 가입
	@Override
	public int memberJoin(MemberVO memberVO) throws Exception {
		return memberMapper.memberJoin(memberVO);
	}

	@Override
	public int idCheck(String string) throws Exception {
		return memberMapper.idCheck(string);
	}
	
	
}
