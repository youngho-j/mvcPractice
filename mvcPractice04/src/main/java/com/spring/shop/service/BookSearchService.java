package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

public interface BookSearchService extends TestMethodService {
	
	// [메인 검색 페이지] 상품 목록
	public List<BookVO> getGoodsList(PageInfo pageInfo);
	
	// [메인 검색 페이지] 상품 전체 갯수
	public int getGoodsTotal(PageInfo pageInfo);
	
	// [관리자 상품 페이지] 상품 목록
	public List<BookVO> adminPageGoodsList(PageInfo pageInfo);
			
	// [관리자 상품 페이지] 상품 총 개수
	public int adminPageGoodsTotal(PageInfo pageInfo);
}
