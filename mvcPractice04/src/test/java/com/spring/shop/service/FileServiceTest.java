package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class FileServiceTest {
	
	@Autowired
	private FileService fileService;
	
	@Test
	public void 이미지_정보_출력_테스트() throws Exception {
		
		List<ImageInfoVO> list = fileService.getImageList(16);
		
		log.info(list.toString());
		
		assertNotNull(list);
	}
	
	@Test
	public void 이미지_경로_목록_출력_테스트() throws Exception {
		List<Path> list = fileService.getImageFilePathList();
		
		assertNull(list);
	}
	
	@Test
	public void 이미지폴더_내부_파일_목록_출력_테스트() throws Exception {
		List<File> list = fileService.getImageFileListInFolder();
		
		assertNull(list);
	}
	
	@Test
	public void 폴더_날자_경로_문자열_값_테스트() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		
		// 현재 날짜에서 하루 전 
		calendar.add(Calendar.DATE, -1);
		
		String datePath = dateFormat.format(calendar.getTime()).replace("-", File.separator);
		
		assertThat(datePath, is("2021\\11\\23"));
	}
	
	@Test
	public void 이미지_비교후_파일삭제_테스트() throws Exception {
		List<Path> dbImageList = null;
		List<File> folderImageList = null;
		
		boolean result = fileService.thinOutFilesInFolder(dbImageList, folderImageList);
		
		assertTrue(result);
	}
}
