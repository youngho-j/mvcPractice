package com.spring.shop.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.spring.shop.service.FileService;
import com.spring.shop.util.FileManager;
import com.spring.shop.util.PathManager;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileController {
	
	private FileService fileService;
	
	private final String uploadRoot = "H:\\mvcPractice04upload";
	
	@Autowired
	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping(value = "/uploadImg", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImageInfoVO>> uploadFilePOST(MultipartFile uploadFile) throws Exception {
		
		List<ImageInfoVO> list = new ArrayList<ImageInfoVO>();
		
		if(ObjectUtils.isEmpty(uploadFile)) {
			log.info("업로드 파일 존재하지 않음");
			return new ResponseEntity<List<ImageInfoVO>>(list, HttpStatus.OK);		
		}	
		
		String variationRoot = new PathManager().getNowPath();
			
		FileManager fileManager = 
				new FileManager.Builder(uploadRoot)
				.fileInfo(uploadFile)
				.variationPath(variationRoot)
				.fileName(uploadFile.getOriginalFilename())
				.build();
			
		// 이미지 파일 타입 체크 
		if(fileManager.MIMETYPECheck()) {
			log.info("파일 타입 확인 - 업로드 가능 이미지 파일");
			// 파일 저장 폴더 생성
			fileManager.createFolder();
				
			// 파일 저장
			list = fileManager.getSavedImagefile();
				
			return new ResponseEntity<List<ImageInfoVO>>(list, HttpStatus.OK);
		}
				
		return new ResponseEntity<List<ImageInfoVO>>(list, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/delUploadImg")
	public ResponseEntity<String> deleteFilePOST(String fileName) {
		log.info("상품 등록 페이지_ ajax 이미지 파일 삭제");
		
		String fixedRoot = "H:\\mvcPractice04upload";
		
		FileManager fileManager = 
				new FileManager.Builder(fixedRoot)
				.fileName(fileName)
				.build();
		
		if(fileManager.deleteImageFile()) {
			return new ResponseEntity<String>("success", HttpStatus.OK);			
		}

		return new ResponseEntity<String>("fail", HttpStatus.NOT_IMPLEMENTED);
	}
	
	// 상품 이미지 호출
	@GetMapping("/display")
	public ResponseEntity<byte[]> showImageGET(String fileName) throws Exception {
		log.info("이미지 파일 출력");
			
		HttpHeaders header = new HttpHeaders();
		
		File file = new File("H:\\mvcPractice04upload", fileName);
		
		// 파일 MIME TYPE 추가
		header.add("Content-type", Files.probeContentType(file.toPath()));
		
		// http response body에 파일을 복사한 바이트 배열, MIME TYPE을 담은 헤더, 상태코드를 담아 리턴
		return new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
	}
		
	// 상품의 이미지 정보 리턴
	@GetMapping(value = "/getImageInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ImageInfoVO>> ImageInfoGET(int bookId) throws Exception {
		log.info("이미지 정보 리턴");
		return new ResponseEntity<List<ImageInfoVO>>(fileService.getImageList(bookId), HttpStatus.OK);
	}
}
