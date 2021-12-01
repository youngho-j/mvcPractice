package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

public interface BookService {
	
	// 상품 목록
	public List<BookVO> getGoodsList(PageInfo pageInfo);
	
	// 상품 전체 갯수
	public int getGoodsTotal(PageInfo pageInfo);
}
