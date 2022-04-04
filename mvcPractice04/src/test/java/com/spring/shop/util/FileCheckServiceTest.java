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
import org.junit.BeforeClass;
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
	
	private static final String fixedRoot = "H:\\mvcPractice04upload";  
	
	private static final String variationRoot = new PathManager().getTheDayBeforePath();  
	
	// 원본 이미지 파일 객체
	private static final File sourceFile = new File("C:\\Users\\admin\\Desktop\\book2.png");
	
	private final String uuidNotStoredInDB = UUID.randomUUID().toString();
	
	private static AuthorVO authorInfo;
	
	private static BookVO bookContainImage;
	
	private static ImageInfoVO bookImage;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private AuthorMapper authorMapper;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private FileCheckService fileCheckService;
	
	@BeforeClass
	public static void setUp() throws Exception {
		// 이미지 등록을 위한 작가, 상품 등록
		// 작가 등록
		authorInfo = new AuthorVO();
		
		authorInfo.setNationId("01");
		authorInfo.setAuthorName("테스트1");
		authorInfo.setAuthorProfile("테스터입니다.");
		
		List<ImageInfoVO> imageList = new ArrayList<ImageInfoVO>();
		
		bookImage = new ImageInfoVO
				.Builder()
				.uploadPath(Paths.get(fixedRoot, variationRoot).toString())
				.uuid(UUID.randomUUID().toString())
				.fileName(sourceFile.getName()).build();
		
		imageList.add(bookImage);

		// 상품 등록
		bookContainImage = new BookVO();
		
		bookContainImage.setBookName("테스트책");
		bookContainImage.setPublicationDate("2022-01-10");
		bookContainImage.setPublisher("한국출판사");
		bookContainImage.setCategoryCode("104000");
		bookContainImage.setBookPrice(20000);
		bookContainImage.setBookStock(50);
		bookContainImage.setBookDiscount(0.2);
		bookContainImage.setBookIntro("책 소개 ");
		bookContainImage.setBookContents("책 목차 ");
		bookContainImage.setImagesList(imageList);
	}
	
	@Before
	public void beforeMethod() throws Exception {
		fileService.deleteAll();
		bookService.deleteAll();
		authorMapper.deleteAll();
		
		authorMapper.authorEnroll(authorInfo);
		
		bookContainImage.setAuthorId(authorMapper.getLastPK());
		
		bookService.goodsEnroll(bookContainImage);
		
		File folderPath = Paths.get(fixedRoot, variationRoot).toFile();
		
		if(!folderPath.exists()) {
			log.info("[{}] 경로를 가진 폴더 생성", folderPath.toPath());
			folderPath.mkdirs();
		}
		
		File ImageOnlyInfolder = Paths.get(fixedRoot, variationRoot + "\\" + bookImage.getUuid() + "_" + sourceFile.getName()).toFile();
		File thumbnailOnlyInfolder = Paths.get(fixedRoot, variationRoot + "\\t_" + bookImage.getUuid() + "_" + sourceFile.getName()).toFile();
		
		File ImageInDB = Paths.get(bookImage.getUploadPath(), uuidNotStoredInDB + "_" + sourceFile.getName()).toFile();
		File thumbnailInDB = Paths.get(bookImage.getUploadPath(), "t_" + uuidNotStoredInDB + "_" + sourceFile.getName()).toFile();
		
		// 파일 복사 - 폴더에만 저장된 파일, DB-폴더 둘다 저장된 파일
		Files.copy(sourceFile.toPath(), ImageOnlyInfolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(sourceFile.toPath(), thumbnailOnlyInfolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
		
		Files.copy(sourceFile.toPath(), ImageInDB.toPath(), StandardCopyOption.REPLACE_EXISTING);
		Files.copy(sourceFile.toPath(), thumbnailInDB.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}
	
	@After
	public void afterMethod() throws Exception {
		// 폴더내 이미지 삭제
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
		
		fileService.deleteAll();
		bookService.deleteAll();
		authorMapper.deleteAll();
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
		
		List<String> list1 = fileService.getTheDayBeforeListOfImgFiles();
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
		
		List<String> list1 = fileService.getTheDayBeforeListOfImgFiles();
		
		List<String> list2 = fileCheckService.getListOfFilesInFolder();
		
		List<String> result = fileCheckService.getUnknownFiles(list1, list2);
		
		fileCheckService.deleteFilesInFolder(result);
		
		File[] filesList = new File(fixedRoot, variationRoot).listFiles();
		
		assertThat(filesList.length, is(2));
	}
}
