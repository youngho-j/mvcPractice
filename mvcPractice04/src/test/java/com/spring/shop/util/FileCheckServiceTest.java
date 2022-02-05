package com.spring.shop.util;

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

import com.spring.shop.mapper.AuthorMapper;
import com.spring.shop.service.BookService;
import com.spring.shop.service.FileService;
import com.spring.shop.vo.AuthorVO;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/test-root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class FileCheckServiceTest {
	
	private final String fixedRoot = "H:\\mvcPractice04upload";  
	
	private final String variationRoot = new PathManager().getTheDayBeforePath();  
	
	private final String uuid = UUID.randomUUID().toString();
	
	private final String uuid2 = UUID.randomUUID().toString();
	
	private AuthorVO author1;
	
	private BookVO book1;
	
	private ImageInfoVO testImage1;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileCheckService fileCheckService;
	
	@Before
	public void setUp() throws Exception {
		bookService.deleteAll();
		authorMapper.deleteAll();
		
		// 이미지 등록을 위한 작가, 상품 등록
		// 작가 등록
		author1 = new AuthorVO();
		
		author1.setNationId("01");
		author1.setAuthorName("테스트1");
		author1.setAuthorProfile("테스터입니다.");
		
		authorMapper.authorEnroll(author1);
		
		int authorId = authorMapper.getLastPK();
		
		File folderPath = Paths.get(fixedRoot, variationRoot).toFile();
		
		// 원본 이미지 파일
		File sourceFile = new File("C:\\Users\\admin\\Desktop\\book2.png");
		
		testImage1 = new ImageInfoVO
				.Builder()
				.uploadPath(Paths.get(fixedRoot, variationRoot).toString())
				.uuid(uuid2)
				.fileName("book2.png").build();
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		imageList.add(testImage1);
		
		// 상품 등록
		book1 = new BookVO();
		
		book1.setBookName("테스트책");
		book1.setAuthorId(authorId);
		book1.setPublicationDate("2022-01-10");
		book1.setPublisher("한국출판사");
		book1.setCategoryCode("104000");
		book1.setBookPrice(20000);
		book1.setBookStock(50);
		book1.setBookDiscount(0.2);
		book1.setBookIntro("책 소개 ");
		book1.setBookContents("책 목차 ");
		book1.setImagesList(imageList);
		
		bookService.goodsEnroll(book1);
		
		if(!folderPath.exists()) {
			log.info("[{}] 경로를 가진 폴더 생성", folderPath.toPath());
			folderPath.mkdirs();
		}
		
		File targetFile1 = Paths.get(fixedRoot, variationRoot + "\\" + uuid + "_book2.png").toFile();
		File targetFile2 = Paths.get(fixedRoot, variationRoot + "\\t_" + uuid + "_book2.png").toFile();
		
		File DBFile1 = Paths.get(testImage1.getUploadPath(), uuid2 + "_book2.png").toFile();
		File DBFile2 = Paths.get(testImage1.getUploadPath(), "t_" + uuid2 + "_book2.png").toFile();
		
		// 파일 복사 - 폴더에만 저장된 파일, DB-폴더 둘다 저장된 파일
		Files.copy(sourceFile.toPath(), targetFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(sourceFile.toPath(), targetFile2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		Files.copy(sourceFile.toPath(), DBFile1.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(sourceFile.toPath(), DBFile2.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	@After
	public void afterTest() throws Exception {
		File targetFolder = new File(fixedRoot);
		
		Files.walk(targetFolder.toPath())
			.sorted(Comparator.reverseOrder())
			.map(Path::toFile)
			.forEach((file)->{
				if(file.getPath().equals(fixedRoot)){
					return;
				}
				file.delete();
			});
	}
	
	@Test
	public void 이미지_폴더내_파일_목록_출력_테스트() throws Exception {
		List<String> list = fileCheckService.getListOfFilesInFolder();
		
		assertThat(list.size(), is(4));
	}
	
	
	@Test
	public void 불필요한_이미지_파일제거_DB_폴더내_이미지가_존재하지_않는경우() throws Exception {
		
		List<String> list1 = new ArrayList<>();
		
		List<String> list2 = new ArrayList<>();
		
		List<String> result = fileCheckService.getUnknownFiles(list1, list2);
		
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void 불필요한_이미지_파일제거_폴더내_이미지만_존재하는경우() throws Exception {
		
		List<String> list1 = new ArrayList<>();
		
		List<String> list2 = fileCheckService.getListOfFilesInFolder();
		
		List<String> result = fileCheckService.getUnknownFiles(list1, list2);
		
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void 불필요한_이미지_파일제거_DB_폴더내_이미지_존재하는경우() throws Exception {
		
		List<String> list1 = fileService.getImageFileList();
		log.info("DB 목록 : {}", list1.toString());
		
		List<String> list2 = fileCheckService.getListOfFilesInFolder();
		log.info("폴더 목록 : {}", list2.toString());
		
		List<String> result = fileCheckService.getUnknownFiles(list1, list2);
		log.info("불필요한 파일 목록 : {}", result.toString());
		
		assertTrue(!result.isEmpty());
	}
	
	@Test
	public void 이미지_파일_삭제_테스트() throws Exception {
		List<String> list = fileCheckService.getListOfFilesInFolder();
		
		fileCheckService.deleteFilesInFolder(list);
        
		File[] filesList = new File(fixedRoot, variationRoot).listFiles();
		
		assertThat(filesList.length, is(0));
	}
	
	@Test
	public void 이미지_파일_삭제_불필요한_이미지파일_존재하지_않을경우() throws Exception {
		
		List<String> list = new ArrayList<>();
		
		fileCheckService.deleteFilesInFolder(list);
		
		File[] filesList = new File(fixedRoot, variationRoot).listFiles();
		
		assertThat(filesList.length, is(4));
	}
	
	@Test
	public void 이미지_파일_삭제_불필요한_이미지파일_존재하는경우() throws Exception {
		
		List<String> list1 = fileService.getImageFileList();
		
		List<String> list2 = fileCheckService.getListOfFilesInFolder();
		
		List<String> result = fileCheckService.getUnknownFiles(list1, list2);
		
		fileCheckService.deleteFilesInFolder(result);
		
		File[] filesList = new File(fixedRoot, variationRoot).listFiles();
		
		assertThat(filesList.length, is(2));
	}
}
