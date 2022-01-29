package com.spring.shop.service;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PathManager;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class FileServiceTest {
	
	@Autowired
	private FileService fileService;
	
	@Before
	public void setUp() throws Exception {
		String variationPath = new PathManager().getTheDayBeforePath();
		
		File folderPath = Paths.get("H:\\mvcPractice04upload", variationPath).toFile();
		
		File sourceFile = new File("C:\\Users\\admin\\Desktop\\book2.png");
		
		File targetFile1 = Paths.get("H:\\mvcPractice04upload", variationPath + "\\book2.png").toFile();
		File targetFile2 = Paths.get("H:\\mvcPractice04upload", variationPath + "\\t_book2.png").toFile();
		
		if(!folderPath.exists()) {
			log.info("[{}] 경로를 가진 폴더 생성", folderPath.toPath());
			folderPath.mkdirs();
			
			Files.copy(sourceFile.toPath(), targetFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(sourceFile.toPath(), targetFile2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	@After
	public void afterTest() throws Exception {
		File targetFolder = new File("H:\\mvcPractice04upload");
		
		Files.walk(targetFolder.toPath())
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach((file)->{
				if(file.getPath().equals("H:\\mvcPractice04upload")){
					return;
				}
				file.delete();
			});
	}
	
	@Test
	public void 이미지_정보_출력_테스트() throws Exception {
		
		List<ImageInfoVO> list = fileService.getImageList(16);
		
		log.info(list.toString());
		
		assertNotNull(list);
	}
	
	@Test
	public void DB에_저장된_이미지_경로_목록_출력_테스트() throws Exception {
		List<String> list = fileService.getImageFileList();
		
		assertNull(list);
	}
	
	@Test
	public void 이미지폴더_내부_파일_목록_출력_테스트() throws Exception {
		List<String> list = fileService.getImageFileListInFolder();
		
		assertThat(list.size(), is(2));
	}
	
	@Test
	public void 이미지_비교후_파일삭제_테스트() throws Exception {
		List<String> list1 = fileService.getImageFileList();
		
		List<String> list2 = fileService.getImageFileListInFolder();
		
		boolean result = fileService.deleteUnknownFiles(list1, list2);
		
		assertTrue(result);
	}
	
	@Test
	public void 이미지_파일_삭제() throws Exception {
		List<ImageInfoVO> infoList = new ArrayList<ImageInfoVO>();
		
		ImageInfoVO vo = new ImageInfoVO();
		vo.setUploadPath("H:\\mvcPractice04upload\\2021\\11\\28");
		vo.setUuid("aeea1b9f-fdb7-48d1-9fe1-e2a73beff993");
		vo.setFileName("book2.png");
		
		ImageInfoVO vo2 = new ImageInfoVO();
		vo2.setUploadPath("H:\\mvcPractice04upload\\2021\\11\\28");
		vo2.setUuid("fded4899-571d-4be1-98c2-9a5ffafcc327");
		vo2.setFileName("book2.png");
		
		infoList.add(vo);
		infoList.add(vo2);
		
		boolean result = fileService.deleteImageFiles(infoList);
		
		assertTrue(result);
	}
}
