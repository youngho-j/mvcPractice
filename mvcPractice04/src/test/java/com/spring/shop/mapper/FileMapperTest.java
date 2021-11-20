package com.spring.shop.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.ImageInfoVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileMapperTest {
	
	@Autowired
	private FileMapper fileMapper;
	
	@Test
	public void 이미지_목록_출력_테스트() throws Exception {
		List<ImageInfoVO> list = fileMapper.getImageList(8);
		
		assertTrue(list.isEmpty());
	}

}
