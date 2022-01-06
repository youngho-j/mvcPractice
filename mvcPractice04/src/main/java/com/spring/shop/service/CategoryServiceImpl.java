package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.CategoryMapper;
import com.spring.shop.vo.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryMapper categoryMapper;
	
	@Autowired
	public CategoryServiceImpl(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}
	
	@Override
	public List<CategoryVO> getCategoryList() {
		log.info("전체 카테고리 목록");
		return categoryMapper.getCategoryList();
	}

	@Override
	public List<CategoryVO> getDomesticCategoryCode() {
		log.info("국내 카테고리 목록");
		return categoryMapper.getDomesticCategoryCode();
	}

	@Override
	public List<CategoryVO> getInternationalCategoryCode() {
		log.info("국외 카테고리 목록");
		return categoryMapper.getInternationalCategoryCode();
	}
	
}
