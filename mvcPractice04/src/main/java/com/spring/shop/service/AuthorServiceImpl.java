package com.spring.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.vo.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	AuthorMapper authorMapper;
	
	@Override
	public int authorEnroll(AuthorVO authorVO) throws Exception {
		return authorMapper.authorEnroll(authorVO);
	}
	
}
