package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.vo.CategoryVO;

public interface CategoryMapper {
	
	// 전체 카테고리 목록
	public List<CategoryVO> getCategoryList();
	
	// 국내 카테고리 목록
	public List<CategoryVO> getDomesticCategoryCode();
	
	// 국외 카테고리 목록
	public List<CategoryVO> getInternationalCategoryCode();
}
