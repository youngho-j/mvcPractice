package com.spring.shop.mapper;

import com.spring.shop.vo.BookVO;

public interface BookMapper {
	
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
		
	// 상품 상세 조회
	public BookVO goodsDetail(int bookId) throws Exception;
		
	// 상품 정보 수정
	public int goodsModify(BookVO bookVO) throws Exception;
		
	// 상품 정보 삭제 (실행 순서 - 이미지 정보 조회 -> 이미지 정보 삭제 -> 상품 정보 삭제)
	public int goodsDelete(int bookId) throws Exception;
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
		
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
	
	// 마지막으로 등록한 상품 정보(테스트용)
	public int getLastPK();
}
