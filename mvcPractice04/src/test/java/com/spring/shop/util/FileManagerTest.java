package com.spring.shop.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class FileManagerTest {

	private FileManager fileManager;
	
	@Before
	public void setUp() throws Exception {
		fileManager = new FileManager("H:\\mvcPractice04upload");
	}
	
	@Test
	public void 폴더_생성_테스트() throws Exception {
		boolean result = fileManager.createFolder();
		
		assertThat(true, is(result));
	}
	
	@Test
	public void 폴더_파일_저장_테스트() throws Exception {
		String fileName = "book2.png";
		String filePath = "C:/Users/admin/Desktop/book2.png";
		
		MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, new FileInputStream(new File(filePath)));
		
		MultipartFile[] multipartFiles = {mockMultipartFile};
		
		List<String> list = fileManager.transferToFolder(multipartFiles, fileManager.getAbsolutepath());
		
		assertNotNull(list.get(0));
	}
}
