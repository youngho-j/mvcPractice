package com.spring.shop.mapper;

import com.spring.shop.vo.BookVO;

public interface AdminMapper {
	
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
	
}
