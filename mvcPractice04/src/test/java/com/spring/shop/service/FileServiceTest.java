package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.ImageInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class FileServiceTest {
	
	@Autowired
	private FileService fileService;
	
	@After
	public void afterMethod() {
		fileService.deleteAll();
	}
	
	@Test
	public void 존재하지않는_이미지_정보_출력_테스트() throws Exception {
		List<ImageInfoVO> list = fileService.getImageList(16);
		
		assertTrue(list.isEmpty());
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void 하루전_저장된_이미지_정보_목록_출력_테스트() throws Exception {
		List<String> list = fileService.getTheDayBeforeListOfImgFiles();
		
		assertThat(list.isEmpty(), is(true));
	}
}
