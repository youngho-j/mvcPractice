package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

public interface AuthorSearchService extends TestMethodService{

	// 작가 목록
	public List<AuthorVO> authorGetList(PageInfo paging) throws Exception;
	
	// 등록된 작가 수
	public int authorGetTotal(PageInfo paging) throws Exception;
	
}
