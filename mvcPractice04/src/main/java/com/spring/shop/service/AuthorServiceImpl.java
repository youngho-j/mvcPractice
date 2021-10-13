package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorMapper authorMapper;
	
	@Override
	public int authorEnroll(AuthorVO authorVO) throws Exception {
		return authorMapper.authorEnroll(authorVO);
	}

	@Override
	public List<AuthorVO> authorGetList(PageInfo paging) throws Exception {
		return authorMapper.authorGetList(paging);
	}

	@Override
	public int authorGetTotal(PageInfo paging) throws Exception {
		return authorMapper.authorGetTotal(paging);
	}
}
