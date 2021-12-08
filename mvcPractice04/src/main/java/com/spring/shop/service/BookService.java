package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

public interface BookService {
	
	// 상품 목록
	public List<BookVO> getGoodsList(PageInfo pageInfo) throws Exception;
	
	// 상품 전체 갯수
	public int getGoodsTotal(PageInfo pageInfo) throws Exception;
	
	// 국내 카테고리 목록
	public List<CategoryVO> getDomesticCategoryCode() throws Exception;
	
	// 국외 카테고리 목록
	public List<CategoryVO> getInternationalCategoryCode() throws Exception;
}
