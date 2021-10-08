package com.spring.shop.mapper;

import com.spring.shop.vo.AuthorVO;

public interface AuthorMapper {
	
	// 작가 등록
	public int authorEnroll(AuthorVO authorVO) throws Exception;
}
