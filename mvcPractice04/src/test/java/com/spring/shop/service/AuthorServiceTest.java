package com.spring.shop.service;

import static org.junit.Assert.*;

import java.util.List;

import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class AuthorServiceTest {
	
	@Autowired
	AuthorService authorService;
	
	@Test
	public void 작가등록_테스트() throws Exception {
		
		AuthorVO authorVO = new AuthorVO();
		
		authorVO.setAuthorName("춘식");
		authorVO.setNationId("02");
		authorVO.setAuthorProfile("춘식입니다");
		
		assertThat(1, is(authorService.authorEnroll(authorVO)));
	}
	
	
	@Test
	public void 작가목록_테스트() throws Exception {
		PagingManager paging = new PagingManager(2, 5);
		
		List<AuthorVO> list = authorService.authorGetList(paging);
		
		assertNotNull(list);
	}
}
