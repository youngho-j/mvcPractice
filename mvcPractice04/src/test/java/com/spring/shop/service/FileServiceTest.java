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
import java.util.UUID;

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
	
	private final String fixedRoot = "H:\\mvcPractice04upload";  
	
	private final String variationRoot = new PathManager().getTheDayBeforePath();  
	
	private final String uuid = UUID.randomUUID().toString();
	
	@Autowired
	private FileService fileService;
	
	@Before
	public void setUp() throws Exception {
		
		File folderPath = Paths.get(fixedRoot, variationRoot).toFile();
		
		File sourceFile = new File("C:\\Users\\admin\\Desktop\\book2.png");
		
		if(!folderPath.exists()) {
			log.info("[{}] 경로를 가진 폴더 생성", folderPath.toPath());
			folderPath.mkdirs();
		}
		
		File targetFile1 = Paths.get(fixedRoot, variationRoot + "\\" + uuid + "_book2.png").toFile();
		File targetFile2 = Paths.get(fixedRoot, variationRoot + "\\t_" + uuid + "_book2.png").toFile();
			
		Files.copy(sourceFile.toPath(), targetFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(sourceFile.toPath(), targetFile2.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
	public void 존재하지않는_이미지_정보_출력_테스트() throws Exception {
		
		List<ImageInfoVO> list = fileService.getImageList(16);
		
		assertNotNull(list);
		assertThat(list.size(), is(0));
	}
	
	@Test
	public void DB에_저장된_이미지_경로_목록_출력_테스트() throws Exception {
		List<String> list = fileService.getTheDayBeforeListOfImgFiles();
		
		assertNull(list);
	}
// 사용되는 곳이 없어 일단 주석 처리
//	@Test
//	public void 이미지_파일_삭제() throws Exception {
//		List<ImageInfoVO> infoList = new ArrayList<ImageInfoVO>();
//		
//		ImageInfoVO vo = new ImageInfoVO();
//		vo.setUploadPath(fixedRoot + "\\" + variationRoot);
//		vo.setUuid(uuid);
//		vo.setFileName("book2.png");
//		
//		infoList.add(vo);
//		
//		boolean result = fileService.deleteImageFiles(infoList);
//		
//		assertTrue(result);
//        
//		File[] filesList = new File(fixedRoot, variationRoot).listFiles();
//		
//		assertThat(filesList.length, is(0));
//	}
}
