package com.spring.shop.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class BookServiceTest {
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void 상품목록_출력_테스트() throws Exception {
		List<BookVO> list = bookService.goodsGetList(new PageInfo(1, 10));
		
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void 상품_개수_출력_테스트() throws Exception {
		int result = bookService.goodsGetTotal(new PageInfo(1, 10));
		
		assertTrue(result > 0);
	}
	
	
}
