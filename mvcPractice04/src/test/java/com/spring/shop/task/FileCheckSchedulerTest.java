package com.spring.shop.task;


import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.shop.util.PathManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class FileCheckSchedulerTest {
	
	private final String firstPath = "H:\\mvcPractice04upload";
	
	private final String variationPath = new PathManager().getTheDayBeforePath();
	
	@Autowired
	FileCheckScheduler scheduler;
	
	@Before
	public void setUp() throws Exception {
		
		File folderPath = Paths.get(firstPath, variationPath).toFile();
		
		File sourceFile = new File("C:\\Users\\admin\\Desktop\\book2.png");
		
		File targetFile1 = Paths.get(firstPath, variationPath + "\\book2.png").toFile();
		File targetFile2 = Paths.get(firstPath, variationPath + "\\t_book2.png").toFile();
		
		if(!folderPath.exists()) {
			log.info("[{}] 경로를 가진 폴더 생성", folderPath.toPath());
			folderPath.mkdirs();
		}
		
		Files.copy(sourceFile.toPath(), targetFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(sourceFile.toPath(), targetFile2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
	}
	
	@After
	public void afterTest() throws Exception {
		File targetFolder = new File(firstPath);
		
		Files.walk(targetFolder.toPath())
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach((file)->{
				if(file.getPath().equals(firstPath)){
					return;
				}
				file.delete();
			});
	}
	
	@Test
	public void 스케쥴러_동작_테스트() throws Exception {
		scheduler.checkNeedlessFiles();
		
		File[] filesList = new File(firstPath, variationPath).listFiles();
		
		assertThat(filesList.length, is(0));
	}

	
}
