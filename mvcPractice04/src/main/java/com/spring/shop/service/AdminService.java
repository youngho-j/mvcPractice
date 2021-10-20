package com.spring.shop.service;

import com.spring.shop.vo.BookVO;

public interface AdminService {
	
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
	
}
