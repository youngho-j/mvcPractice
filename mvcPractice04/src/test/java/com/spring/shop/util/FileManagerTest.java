package com.spring.shop.util;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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

import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
public class FileManagerTest {

	private FileManager fileManager;
	
	@Before
	public void setUp() throws Exception {
		
		// 테스트 파일 이름 및 경로 
		String fileName = "book2.png";
		String filePath = "C:\\Users\\admin\\Desktop";
				
		// MockMultipartFile 생성
		MultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName, "image/png", new FileInputStream(new File(filePath, fileName)));
		
		fileManager = new FileManager
				.Builder("H:\\mvcPractice04upload")
				.fileInfo(mockMultipartFile)
				.variationPath("2022\\01\\20")
				.build();
	}
	
	@After
	public void afterTest() throws Exception {
		File targetFolder = new File(fileManager.getFixedPath());
		
		Files.walk(targetFolder.toPath())
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach((file)->{
				if(file.getPath().equals(fileManager.getFixedPath())){
					return;
				}
				log.info("{} >> 삭제되었습니다.", file.getPath());
				file.delete();
			});
	}
	
	@Test
	public void 폴더_생성_테스트() throws Exception {
		
		boolean result = fileManager.createFolder();
		
		assertThat(true, is(result));
	}
	
	@Test
	public void 이미지_파일_저장_테스트() throws Exception {
		
		fileManager.createFolder();
		
		MultipartFile fileInfo = fileManager.getFileInfo();
		
		String fileName = fileInfo.getOriginalFilename();
		
		String uploadRoot = fileManager.getFixedPath() + File.separator + fileManager.getVariationPath();
		
		File destFile = new File(uploadRoot, fileName);
		
		fileManager.saveImageFile(fileInfo, destFile);
		log.info("{} >> 저장되었습니다.", destFile.toPath());
		
		assertTrue(destFile.exists());
	}
	
	@Test
	public void 썸네일_파일_저장_테스트() throws Exception {
		
		fileManager.createFolder();
		
		MultipartFile fileInfo = fileManager.getFileInfo();
		
		String fileName = fileInfo.getOriginalFilename();
		
		String thumbFileName = "t_" + fileName;
		
		String uploadRoot = fileManager.getFixedPath() + File.separator + fileManager.getVariationPath();
		
		File destFile = new File(uploadRoot, fileName);
		File thumbFile = new File(uploadRoot, thumbFileName);
		
		fileManager.saveImageFile(fileInfo, destFile);
		log.info("{} >> 저장되었습니다.", destFile.toPath());
			
		fileManager.saveThumbnail(thumbFile, destFile);
		log.info("{} >> 저장되었습니다.", thumbFile.toPath());
		
		assertTrue(thumbFile.exists());
	}
	
	@Test
	public void 파일_MIME_TYPE_확인_테스트() throws Exception {
		
		boolean result = fileManager.MIMETYPECheck();
		
		assertTrue(result);
		
	}
	
	@Test
	public void 폴더내_저장된_파일목록_테스트() throws Exception {
		File targetFolder = new File(fileManager.getFixedPath(), fileManager.getVariationPath());
		
		fileManager.createFolder();
		
		List<ImageInfoVO> imageList = fileManager.getSavedImagefile();
		
		File[] result = targetFolder.listFiles();
		
		assertThat(imageList.size(), is(1));
		
		assertThat(result.length, is(2));
	}
}
