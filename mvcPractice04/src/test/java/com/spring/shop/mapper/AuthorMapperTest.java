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
import com.spring.shop.vo.AuthorVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Test
	public void 작가_등록_메서드_테스트() throws Exception {
		AuthorVO authorVO = new AuthorVO();
		
		authorVO.setNationId("02");
		authorVO.setAuthorName("프로도");
		authorVO.setAuthorProfile("프로도에요");
		
		log.info(authorVO.toString());
		
		int result = authorMapper.authorEnroll(authorVO);
		
		assertThat(1, is(result));
	}

	@Test
	public void 작가목록_출력_메서드_테스트() throws Exception {
		PageInfo paging = new PageInfo(2, 10);
		
		List<AuthorVO> list = authorMapper.authorGetList(paging);
		
		assertNotNull(list);
		
		log.info(list.toString());
		
	}
	
	@Test
	public void 등록된_작가수_카운팅_메서드_테스트() throws Exception {
		PageInfo paging = new PageInfo();
		
		int total = authorMapper.authorGetTotal(paging);
		
		assertThat(6, is(total));
	}
	
	@Test
	public void 작가_상세_정보_메서드_테스트() throws Exception {
		int authorId = 1;
		
		AuthorVO authorDetail = authorMapper.authorGetDetail(authorId);
		
		assertThat("무지", is(authorDetail.getAuthorName()));
	}
}
