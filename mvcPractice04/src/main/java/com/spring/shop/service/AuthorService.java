package com.spring.shop.service;

import com.spring.shop.vo.AuthorVO;

public interface AuthorService {

	// 작가 등록
	public int authorEnroll(AuthorVO authorVO) throws Exception;
}
