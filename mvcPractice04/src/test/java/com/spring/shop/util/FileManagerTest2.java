package com.spring.shop.util;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import static org.hamcrest.core.Is.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml"})
public class FileManagerTest2 {

	private FileManager2 fileManager;
	
	@Before
	public void setUp() throws Exception {
		fileManager = new FileManager2
				.Builder("H:\\mvcPractice04upload")
				.variationPath("2022/01/20")
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
				log.info("{} >>> 삭제되었습니다.", file.getPath());
				file.delete();
			});
		
	}
	
	@Test
	public void 폴더_생성_테스트() throws Exception {
		
		boolean result = fileManager.createFolder();
		
		assertThat(true, is(result));
	}
	
	
}
