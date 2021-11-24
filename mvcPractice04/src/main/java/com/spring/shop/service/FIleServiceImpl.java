package com.spring.shop.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.FileMapper;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FIleServiceImpl implements FileService{

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public List<ImageInfoVO> getImageList(int bookId) throws Exception {
		log.info("이미지 정보 출력 실행");
		return fileMapper.getImageList(bookId);
	}

	@Override
	public List<Path> getImageFilePathList() throws Exception {
		// 이미지 파일 경로를 담을 객체
		List<Path> ImageFilePathList = new ArrayList<Path>();
		
		List<ImageInfoVO> imageInfoList = fileMapper.getImageListCheck();
		
		imageInfoList.forEach(imageInfo -> {
			Path imagePath = Paths.get(imageInfo.getUploadPath(), imageInfo.getUuid() + "_" + imageInfo.getFileName());
			Path thumbPath = Paths.get(imageInfo.getUploadPath(), "t_" + imageInfo.getUuid() + "_" + imageInfo.getFileName());
			
			ImageFilePathList.add(imagePath);
			ImageFilePathList.add(thumbPath);
			
			imagePath = null;
			thumbPath = null;
		});
		
		return ImageFilePathList;
	}
	
	@Override
	public File[] getImageFileListInFolder() throws Exception {
		// 폴더 경로를 가진 파일 객체 생성
		File folderPath = Paths.get("H:\\mvcPractice04upload", getFolderDatePath()).toFile();
		
		// 경로 안에 있는 모든 파일 정보를 배열에 담아 리턴
		return folderPath.listFiles();
	}

	// 폴더 날자 경로 문자열 값 리턴 
	private String getFolderDatePath() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar calendar = Calendar.getInstance();
		
		// 현재 날짜에서 하루 전 
		calendar.add(Calendar.DATE, -1);
		
		String datePath = dateFormat.format(calendar.getTime()).replace("-", File.separator);
		
		return datePath;
	}
	
}
