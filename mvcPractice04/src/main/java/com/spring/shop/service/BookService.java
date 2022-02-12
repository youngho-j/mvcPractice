package com.spring.shop.service;

import java.util.List;

import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

public interface BookService extends TestMethodService{
	// 상품 등록
	public int goodsEnroll(BookVO bookVO) throws Exception;
		
	// 상품 상세정보 조회
	public BookVO goodsDetail(int bookId) throws Exception;
		
	// 상품 정보 수정
	public int goodsModify(BookVO bookVO) throws Exception;
		
	// 상품 정보 삭제
	public List<ImageInfoVO> goodsDelete(int bookId) throws Exception;
}
