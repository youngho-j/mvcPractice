package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

public interface BookSearchMapper {
	
	// [메인 검색 페이지] 상품 목록
	public List<BookVO> getGoodsList(PageInfo pageInfo);
	
	// [메인 검색 페이지] 상품 전체 갯수
	public int getGoodsTotal(PageInfo pageInfo);
	
	// [메인 검색 페이지] 작가 id 목록
	public List<String> getAuthorIdList(String keyword);
	
	// [관리자 상품 페이지] 상품 목록
	public List<BookVO> adminPageGoodsList(PageInfo pageInfo);
		
	// [관리자 상품 페이지] 상품 총 개수
	public int adminPageGoodsTotal(PageInfo pageInfo);
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
		
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
	
	// 마지막으로 등록한 책 정보(테스트용)
	public int getLastPK();
}
