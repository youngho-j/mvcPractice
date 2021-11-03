package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	@Override
	public AuthorVO authorGetDetail(int authorId) throws Exception {
		return authorMapper.authorGetDetail(authorId);
	}

	@Override
	public int authorModify(AuthorVO authorVO) throws Exception {
		return authorMapper.authorModify(authorVO);
	}

	@Override
	public int authorDelete(int authorId) throws Exception {
		// 작가의 ID로 등록된 책이 없을 경우 
		if(authorMapper.authorWrittenBook(authorId) == 0) {
			log.info("작가 정보 삭제");
			return authorMapper.authorDelete(authorId);			
		}		
		log.info("등록된 책 정보로 인하여 작가 정보 삭제 불가");
		return 2;
	}
}
