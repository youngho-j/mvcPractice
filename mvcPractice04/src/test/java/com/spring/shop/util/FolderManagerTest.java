package com.spring.shop.util;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/security-context.xml"})
public class FolderManagerTest {

	private FolderManager folderManager;
	
	@Before
	public void setUp() throws Exception {
		folderManager = new FolderManager("H:\\mvcPractice04upload");
	}
	
	@Test
	public void 폴더_생성_테스트() throws Exception {
		boolean result = folderManager.createFolder();
		
		assertThat(true, is(result));
	}

}
