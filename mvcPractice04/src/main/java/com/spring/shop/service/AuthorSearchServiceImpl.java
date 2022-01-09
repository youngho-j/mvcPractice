package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.AuthorSearchMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorSearchServiceImpl implements AuthorSearchService {

	private final AuthorSearchMapper authorSearchMapper;	
	
	@Autowired
	public AuthorSearchServiceImpl(AuthorSearchMapper authorSearchMapper) {
		this.authorSearchMapper = authorSearchMapper;
	}

	@Override
	public List<AuthorVO> authorGetList(PageInfo paging) throws Exception {
		log.info("작가 목록");
		return authorSearchMapper.authorGetList(paging);
	}

	@Override
	public int authorGetTotal(PageInfo paging) throws Exception {
		log.info("등록된 작가 수");
		return authorSearchMapper.authorGetTotal(paging);
	}

	@Override
	public void deleteAll() {
		authorSearchMapper.deleteAll();
	}

	@Override
	public int getCount() {
		return authorSearchMapper.getCount();
	}

	@Override
	public int getLastPK() {
		return authorSearchMapper.getLastPK();
	}
}
