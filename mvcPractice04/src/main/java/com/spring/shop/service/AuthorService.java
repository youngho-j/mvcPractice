package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

public interface AuthorService {

	// 작가 등록
	public int authorEnroll(AuthorVO authorVO) throws Exception;
	
	// 작가 목록
	public List<AuthorVO> authorGetList(PageInfo paging) throws Exception;
}
