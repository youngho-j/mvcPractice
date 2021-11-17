package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;
import com.spring.shop.vo.ImageInfoVO;

public interface AdminMapper {
	
	// 상품 등록
	public int bookEnroll(BookVO bookVO) throws Exception;
	
	// 카테고리 목록
	public List<CategoryVO> categoryList() throws Exception;
	
	// 상품 리스트
	public List<BookVO> goodsList(PageInfo pageInfo) throws Exception;
	
	// 상품 총 개수
	public int goodsTotal(PageInfo pageInfo) throws Exception;
	
	// 상품 상세 조회
	public BookVO goodsDetail(int bookId) throws Exception;
	
	// 상품 정보 수정
	public int goodsModify(BookVO bookVO) throws Exception;
	
	// 상품 정보 삭제
	public int goodsDelete(int bookId) throws Exception;
	
	// 상품 이미지 등록
	public int imageEnroll(ImageInfoVO imageInfo) throws Exception;
}
