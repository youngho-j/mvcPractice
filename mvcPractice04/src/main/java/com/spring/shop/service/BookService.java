package com.spring.shop.service;

import com.spring.shop.vo.BookVO;

public interface BookService extends TestMethodService{
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
		
	// 상품 상세정보 조회
	public BookVO goodsDetail(int bookId) throws Exception;
		
	// 상품 정보 수정
	public int goodsModify(BookVO bookVO) throws Exception;
		
	// 상품 정보 삭제
	public int goodsDelete(int bookId) throws Exception;
}
