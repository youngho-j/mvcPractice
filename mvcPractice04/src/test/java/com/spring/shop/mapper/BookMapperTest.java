package com.spring.shop.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookMapperTest {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Test
	public void 상품목록_리턴_테스트() throws Exception {
		List<BookVO> list = bookMapper.goodsGetList(new PageInfo(1, 10));
		
		log.info(list.toString());
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void 상품_전체_갯수_리턴_테스트() throws Exception {
		int result = bookMapper.getGoodsTotal(new PageInfo(1, 10));
		
		assertTrue(result > 0);
	}
	
	@Test
	public void 작가_아이디_목록_리턴_테스트() throws Exception {
		String[] list = bookMapper.getAuthorIdList("봉");
		
		assertTrue(list.length > 0);
	}
	
}
