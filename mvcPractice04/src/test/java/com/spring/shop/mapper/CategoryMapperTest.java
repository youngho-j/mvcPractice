package com.spring.shop.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.CategoryVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class CategoryMapperTest {
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test
	public void 전체_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = categoryMapper.getCategoryList();
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void 국내_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = categoryMapper.getDomesticCategoryCode();
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void 국외_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = categoryMapper.getInternationalCategoryCode();
		
		assertFalse(list.isEmpty());
	}
	
}
