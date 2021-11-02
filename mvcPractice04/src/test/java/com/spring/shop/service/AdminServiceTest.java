package com.spring.shop.service;

import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.CategoryVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
	@Test
	public void 책등록_테스트() throws Exception {
		BookVO book = new BookVO();
		
		book.setBookName("service 테스트");
		book.setAuthorId(77);
		book.setPublicationDate("2021-10-20");
		book.setPublisher("해냄");
		book.setCategoryCode("0231");
		book.setBookPrice(30000);
		book.setBookStock(100);
		book.setBookDiscount(0.15);
		book.setBookIntro("소개");
		book.setBookContents("목차");
		
		int result = adminService.bookEnroll(book);
		
		assertThat(1, is(result));
	}
	
	@Test
	public void 카테고리목록_테스트() throws Exception {
		List<CategoryVO> list = adminService.categoryList();
		
		assertNotNull(list);
	}
	
	@Test
	public void 상품리스트_테스트() throws Exception {
		PageInfo pageInfo = new PageInfo(1, 10);
		
		List<BookVO> list = adminService.goodsList(pageInfo);
		
		assertThat(0, is(list.size()));
	}
	
	@Test
	public void 상품총개수_테스트() throws Exception {
		PageInfo pageInfo = new PageInfo(1, 10);
		
		int result = adminService.goodsTotal(pageInfo);
		
		assertThat(0, is(result));
	}
	
	@Test
	public void 상품_상세조회_테스트() throws Exception {
		BookVO detail = adminService.goodsDetail(8);
		
		assertThat(detail.getAuthorName(), is("신형만"));
	}
	
	@Test
	public void 상품정보_수정_테스트() throws Exception {
		BookVO modify = new BookVO();
		
		modify.setBookId(8);
		modify.setBookName("찍먹한국사");
		modify.setAuthorId(22);
		modify.setPublicationDate("2021-10-14");
		modify.setPublisher("한빛");
		modify.setCategoryCode("106003");
		modify.setBookPrice(30000);
		modify.setBookStock(15);
		modify.setBookDiscount(0.2);
		modify.setBookIntro("<p>테스트</p>");
		modify.setBookContents("<p>테스트</p>");
		
		int result = adminService.goodsModify(modify);
		
		assertThat(1, is(result));
	}
	
	@Test
	public void 상품정보_삭제_테스트() throws Exception {
		int result = adminService.goodsDelete(10);
		
		assertThat(1, is(result));
	}
}
