package com.spring.shop.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.core.Is.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
public class FileManagerTest2 {

	private FileManager2 fileManager1;
	
	@Before
	public void setUp() throws Exception {
		List<MultipartFile> fileList = new ArrayList<MultipartFile>();
		
		// 테스트 파일 이름 및 경로 
		String fileName = "book2.png";
		String filePath = "C:\\Users\\admin\\Desktop";
				
		// MockMultipartFile 생성
		MultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName, "image/png", new FileInputStream(new File(filePath, fileName)));
		
		fileList.add(mockMultipartFile);
		
		fileManager1 = new FileManager2
				.Builder("H:\\mvcPractice04upload", fileList)
				.variationPath("2022\\01\\20")
				.build();
	}
	
	@After
	public void afterTest() throws Exception {
		File targetFolder = new File(fileManager1.getFixedPath());
		
		Files.walk(targetFolder.toPath())
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach((file)->{
				if(file.getPath().equals(fileManager1.getFixedPath())){
					return;
				}
				log.info("{} >> 삭제되었습니다.", file.getPath());
				file.delete();
			});
	}
	
	@Test
	public void 폴더_생성_테스트() throws Exception {
		
		boolean result = fileManager1.createFolder();
		
		assertThat(true, is(result));
	}
	
	@Test
	public void 파일_저장_테스트() throws Exception {
		
		fileManager1.createFolder();
		
		List<MultipartFile> fileList = fileManager1.getFileList();
		
		String fileName = fileList.get(0).getOriginalFilename();
		
		File destFile = new File(fileManager1.getFixedPath() + File.separator + fileManager1.getVariationPath(), fileName);
		
		for(MultipartFile file : fileList) {
			fileManager1.saveImageFile(file, destFile);
			
			log.info("{} >> 저장되었습니다.", destFile.toPath());
		}
		
		assertTrue(destFile.exists());
		
		
	}
	
}
