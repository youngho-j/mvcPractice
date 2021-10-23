package com.spring.shop.service;

import java.util.List;

import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

public interface AdminService {
	
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
	
	// 카테고리 목록
	public List<CategoryVO> categoryList() throws Exception;
	
}
