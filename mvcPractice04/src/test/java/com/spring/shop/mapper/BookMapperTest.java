package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

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
		PageInfo info = new PageInfo(1, 10);
		info.setType("AT");
		info.setKeyword("한");
		info.setAuthorList(new String[] {"22"});
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		log.info(list.toString());
		
		assertThat(2, is(list.size()));
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
		String[] list = bookMapper.getAuthorIdList("봉");
		
		assertTrue(list.length > 0);
	}
	
}
