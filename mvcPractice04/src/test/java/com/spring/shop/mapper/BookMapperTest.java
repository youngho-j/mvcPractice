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
import com.spring.shop.vo.CategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class BookMapperTest {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Test
	public void 작가목록에_존재하지_않는_작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookMapper.getAuthorIdList("1 or 1=1");
		
		assertThat(0, is(list.size()));
	}
	
	@Test
	public void 작가목록에_존재하는_작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookMapper.getAuthorIdList("식");
		
		assertThat(2, is(list.size()));
	}
	
	@Test
	public void 키워드_제목_검색_목록_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("T");
		info.setKeyword("한");
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		assertThat(2, is(list.size()));
	}
	
	@Test
	public void 잘못된_키워드_작가_검색_목록_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("A");
		info.setKeyword("-");
		
		List<String> authorList = bookMapper.getAuthorIdList(info.getKeyword());
		
		info.setAuthorIdList(authorList);
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		assertThat(0, is(list.size()));
	}
	
	@Test
	public void 작가_검색_목록_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("A");
		info.setKeyword("신");
		
		List<String> authorList = bookMapper.getAuthorIdList(info.getKeyword());
		
		info.setAuthorIdList(authorList);
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		assertThat(2, is(list.size()));
	}
	
	@Test
	public void 제목_작가_검색_목록_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("AT");
		info.setKeyword("한");
		
		List<String> authorList = bookMapper.getAuthorIdList(info.getKeyword());
		
		info.setAuthorIdList(authorList);
		
		List<BookVO> list = bookMapper.getGoodsList(info);
		
		assertThat(2, is(list.size()));
	}
	
	@Test
	public void 제목_키워드_상품_전체_개수_리턴_테스트() throws Exception {
		PageInfo info = new PageInfo(1, 10);
		info.setType("T");
		info.setKeyword("한");
		
		int result = bookMapper.getGoodsTotal(info);
		
		assertTrue(result > 0);
	}
	
	
	@Test
	public void 국내_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = bookMapper.getDomesticCategoryCode();
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void 국외_카테고리_목록_리턴_테스트() throws Exception {
		List<CategoryVO> list = bookMapper.getInternationalCategoryCode();
		
		assertFalse(list.isEmpty());
	}
	
}
