package com.spring.shop.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.CategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class CategoryServiceTest {
	
	@Autowired
	private CategoryService cateService;

	@Test
	public void 전체_카테고리목록_테스트() throws Exception {
		List<CategoryVO> list = cateService.getCategoryList();
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void 국내_카테고리목록_테스트() throws Exception {
		List<CategoryVO> list = cateService.getDomesticCategoryCode();
		
		assertTrue(!list.isEmpty());
	}
	
	@Test
	public void 국외_카테고리목록_테스트() throws Exception {
		List<CategoryVO> list = cateService.getInternationalCategoryCode();
		
		assertTrue(!list.isEmpty());
	}
	
	
}
