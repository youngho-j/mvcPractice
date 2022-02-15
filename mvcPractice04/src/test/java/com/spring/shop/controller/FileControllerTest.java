package com.spring.shop.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.shop.util.PathManager;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@WithMockUser(username = "testUser", roles = {"ADMIN"})
public class FileControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;
	
	private final String fixedRoot = "H:\\mvcPractice04upload";  
	
	private final String variationRoot = new PathManager().getTheDayBeforePath();  
	
	private final String uuid = UUID.randomUUID().toString();
	
	private File sourceFile = new File("C:\\Users\\admin\\Desktop\\book2.png");
	
	@Before
	public void setUp() throws Exception {
		this.mock = MockMvcBuilders
				.webAppContextSetup(wac)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		File folderPath = Paths.get(fixedRoot, variationRoot).toFile();
		
		if(!folderPath.exists()) {
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
	public void 업로드_이미지_삭제_테스트() throws Exception {
		// 테스트 파일 이름 및 경로 
		String fileName = new StringBuilder()
				.append(variationRoot)
				.append("\\")
				.append(uuid)
				.append("_book2.png").toString();
		
		mock.perform(post("/delUploadImg").with(csrf())
				.param("fileName", fileName))
		.andExpect(status().isOk())
		.andExpect(content().string("success"))
		.andDo(print());
		
	}
	
	@Test
	public void 이미지_정보_JSON_리턴_테스트() throws Exception {
		mock.perform(get("/getImageInfo")
				.param("bookId", "16"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print());
	}
	
	@Test
	public void 존재하는_이미지파일_호출_테스트() throws Exception {
		mock.perform(get("/display")
				.param("fileName", variationRoot + "\\t_" + uuid + "_book2.png"))
		.andExpect(status().isOk())
		.andExpect(header().exists("Content-type"))
		.andExpect(content().contentType(MediaType.IMAGE_PNG_VALUE))
		.andDo(print());
	}
	
	@Test
	public void 존재하지않는_이미지파일_호출_테스트() throws Exception {
		mock.perform(get("/display")
				.param("fileName", variationRoot + "\\t_book2.png"))
		.andExpect(status().isOk())
		.andExpect(header().exists("Content-type"))
		.andExpect(content().contentType(MediaType.IMAGE_PNG_VALUE))
		.andDo(print());
	}
	
	@Test
	public void 파일_업로드_테스트() throws Exception {
		MockMultipartFile uploadFile = 
				new MockMultipartFile("uploadFile","book2.png","image/png", new FileInputStream(sourceFile));
		
		mock.perform(multipart("/uploadImg")
				.file(uploadFile).with(csrf()))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
	@Test
	public void 파일_업로드_파일없는경우_테스트() throws Exception {
		
		mock.perform(multipart("/uploadImg")
				.with(csrf()))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
}
