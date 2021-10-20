package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class AdminServiceTest {
	
	@Autowired
	private AdminService AdminService;
	
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
		
		int result = AdminService.bookEnroll(book);
		
		assertThat(1, is(result));
	}

}
