package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

public interface AdminMapper {
	
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
	
	// 카테고리 목록
	public List<CategoryVO> categoryList() throws Exception;
	
	// 상품 리스트
	public List<BookVO> goodsList(PageInfo pageInfo) throws Exception;
	
	// 상품 총 개수
	public int goodsTotal(PageInfo pageInfo) throws Exception;
}
