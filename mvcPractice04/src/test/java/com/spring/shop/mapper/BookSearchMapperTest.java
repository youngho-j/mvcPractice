package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/test-root-context.xml")
public class BookSearchMapperTest {
	
	@Autowired
	private BookSearchMapper bookSearchMapper;
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	private BookVO book1;
	private BookVO book2;
	
	private AuthorVO author1;
	
	@Before
	public void setUp() {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
		
		// 작가 id와 외래키로 연동되어 있어 메서드 실행 전 작가 등록 필요
		author1 = new AuthorVO();
		
		author1.setNationId("01");
		author1.setAuthorName("테스트1");
		author1.setAuthorProfile("테스터입니다.");
		
		authorMapper.authorEnroll(author1);
		
		int authorId = authorMapper.getLastPK();
		
		book1 = new BookVO();
		book2 = new BookVO();
		
		book1.setBookName("테스트책");
		book1.setAuthorId(authorId);
		book1.setPublicationDate("2022-01-10");
		book1.setPublisher("한국출판사");
		book1.setCategoryCode("104000");
		book1.setBookPrice(20000);
		book1.setBookStock(50);
		book1.setBookDiscount(0.2);
		book1.setBookIntro("책 소개 ");
		book1.setBookContents("책 목차 ");
		
		book2.setBookName("테스트책2");
		book2.setAuthorId(authorId);
		book2.setPublicationDate("2022-01-10");
		book2.setPublisher("미국출판사");
		book2.setCategoryCode("104000");
		book2.setBookPrice(20000);
		book2.setBookStock(10);
		book2.setBookDiscount(0.4);
		book2.setBookIntro("책 소개 ");
		book2.setBookContents("책 목차 ");
	}
	
	@After
	public void afterTest() {
		bookMapper.deleteAll();
		authorMapper.deleteAll();
	}
	
	@Test
	public void setUp_메서드_테스트() throws Exception {
		
		assertThat(authorMapper.getCount(), is(1));
		
		assertThat(bookMapper.getCount(), is(0));
		
		bookMapper.bookEnroll(book1);
		assertThat(bookMapper.getCount(), is(1));
		
		bookMapper.bookEnroll(book2);
		assertThat(bookMapper.getCount(), is(2));
	}
	
	@Test
	public void 작가목록에_존재하지_않는_작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookSearchMapper.getAuthorIdList("1 or 1=1");
		
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void 작가목록에_존재하는_작가_아이디_목록_리턴_테스트() throws Exception {
		List<String> list = bookSearchMapper.getAuthorIdList("테");
		
		assertThat(list.size(), is(1));
	}
	
	@Test
	public void 키워드_제목_검색_목록_리턴_테스트() throws Exception {
		bookMapper.bookEnroll(book1);
		bookMapper.bookEnroll(book2);
		
		PageInfo info = new PageInfo(1, 10);
		info.setType("T");
		info.setKeyword("테스트");
		
		List<BookVO> list = bookSearchMapper.getGoodsList(info);
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 잘못된_키워드_작가_검색_목록_리턴_테스트() throws Exception {
		bookMapper.bookEnroll(book1);
		bookMapper.bookEnroll(book2);
		
		PageInfo info = new PageInfo(1, 10);
		info.setType("A");
		info.setKeyword("!");
		
		List<String> authorList = bookSearchMapper.getAuthorIdList(info.getKeyword());
		
		info.setAuthorIdList(authorList);
		
		List<BookVO> list = bookSearchMapper.getGoodsList(info);
		
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void 작가_검색_목록_리턴_테스트() throws Exception {
		bookMapper.bookEnroll(book1);
		bookMapper.bookEnroll(book2);
		
		PageInfo info = new PageInfo(1, 10);
		info.setType("A");
		info.setKeyword("테");
		
		List<String> authorList = bookSearchMapper.getAuthorIdList(info.getKeyword());
		
		info.setAuthorIdList(authorList);
		
		List<BookVO> list = bookSearchMapper.getGoodsList(info);
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 제목_작가_검색_목록_리턴_테스트() throws Exception {
		bookMapper.bookEnroll(book1);
		bookMapper.bookEnroll(book2);
		
		PageInfo info = new PageInfo(1, 10);
		info.setType("AT");
		info.setKeyword("테");
		
		List<String> authorList = bookSearchMapper.getAuthorIdList(info.getKeyword());
		
		info.setAuthorIdList(authorList);
		
		List<BookVO> list = bookSearchMapper.getGoodsList(info);
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 제목_키워드_상품_전체_개수_리턴_테스트() throws Exception {
		bookMapper.bookEnroll(book1);
		bookMapper.bookEnroll(book2);
		
		PageInfo info = new PageInfo(1, 10);
		info.setType("T");
		info.setKeyword("테");
		
		int result = bookSearchMapper.getGoodsTotal(info);
		
		assertTrue(result > 0);
	}
}
