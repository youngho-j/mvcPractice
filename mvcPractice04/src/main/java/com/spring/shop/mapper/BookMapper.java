package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

public interface BookMapper {
	
	// 상품 목록
	public List<BookVO> getGoodsList(PageInfo pageInfo);
	
	// 상품 전체 갯수
	public int getGoodsTotal(PageInfo pageInfo);
	
	// 작가 id 목록
	public List<String> getAuthorIdList(String keyword);
	
	// 국내 카테고리 목록
	public List<CategoryVO> getDomesticCategoryCode() throws Exception;
	
	// 국외 카테고리 목록
	public List<CategoryVO> getInternationalCategoryCode() throws Exception;
}
