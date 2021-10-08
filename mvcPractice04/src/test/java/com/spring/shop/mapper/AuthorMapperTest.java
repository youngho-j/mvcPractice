package com.spring.shop.mapper;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
