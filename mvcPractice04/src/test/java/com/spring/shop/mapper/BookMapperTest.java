package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Test
	public void 작가_제목_검색_목록_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("T");
		info.setKeyword("한");
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		log.info(list.toString());
		
		assertThat(2, is(list.size()));
	}
	
	@Test
	public void 작가_잘못된_검색_목록_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("A");
		info.setKeyword("활");
		info.setAuthorIdList(new ArrayList<String>());
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		log.info(list.toString());
		
		assertThat(0, is(list.size()));
	}
	
	@Test
	public void 상품_전체_갯수_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("T");
		info.setKeyword("한");
		
		int result = bookMapper.getGoodsTotal(info);
		
		log.info("검색된 책 개수 : " + result);
		
		assertTrue(result > 0);
	}
	
	@Test
	public void 작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookMapper.getAuthorIdList("천");
		
		assertThat(0, is(list.size()));
	}
	
	@Test
	public void 국내_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = bookMapper.getDomesticCategoryCode();
		
		log.info("카테고리 목록 : " + list.toString());
		
		assertTrue(!list.isEmpty());
	}
	
	@Test
	public void 국외_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = bookMapper.getInternationalCategoryCode();
		
		log.info("카테고리 목록 : " + list.toString());
		
		assertTrue(!list.isEmpty());
	}
	
}
