package com.spring.shop.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService{

	private final AuthorMapper authorMapper;	
	
	@Autowired
	public AuthorServiceImpl(AuthorMapper authorMapper) {
		this.authorMapper = authorMapper;
	}

	@Override
	public int authorEnroll(AuthorVO authorVO) throws Exception {
		log.info("작가 정보 등록");
		return authorMapper.authorEnroll(authorVO);
	}

	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		log.info("작가 상세 정보");
		return authorMapper.authorGetDetail(authorId);
	}

	@Override
	public int authorModify(AuthorVO authorVO) throws Exception {
		log.info("작가 정보 수정");
		return authorMapper.authorModify(authorVO);
	}

	@Override
	public int authorDelete(int authorId) throws Exception {
		log.info("작가 정보 삭제");
		// 작가의 ID로 등록된 책이 없을 경우 
		if(authorMapper.authorWrittenBook(authorId) == 0) {
			log.info("작가 정보 삭제");
			return authorMapper.authorDelete(authorId);			
		}		
		log.info("등록된 책 정보로 인하여 작가 정보 삭제 불가");
		return 2;
	}

	@Override
	public void deleteAll() {
		authorMapper.deleteAll();
	}

	@Override
	public int getCount() {
		return authorMapper.getCount();
	}

	@Override
	public int getLastPK() {
		return authorMapper.getLastPK();
	}
}
