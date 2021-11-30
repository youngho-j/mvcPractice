package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

public interface BookService {
	
	// 상품 목록
	public List<BookVO> goodsGetList(PageInfo pageInfo);
	
	// 상품 전체 갯수
	public int goodsGetTotal(PageInfo pageInfo);
}
