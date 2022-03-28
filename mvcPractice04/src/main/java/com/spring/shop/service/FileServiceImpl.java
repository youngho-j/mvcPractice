package com.spring.shop.service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.FileMapper;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public List<ImageInfoVO> getImageList(int bookId) throws Exception {
		log.info("이미지 정보 출력 실행");
		return fileMapper.getImageList(bookId);
	}

	@Override
	public List<String> getTheDayBeforeListOfImgFiles() throws Exception {
		log.info("하루 전 저장된 이미지 정보 목록");
		
		// 이미지 파일 경로를 담을 객체
		List<String> ImageFilePathList = new ArrayList<>();
		
		List<ImageInfoVO> imageInfoList = fileMapper.getPreviousImageList();
		
		if(!imageInfoList.isEmpty()) {
			
			StringBuilder sb = new StringBuilder();
			
			imageInfoList.forEach(imageInfo -> {
				
				sb.append("t_").append(imageInfo.getUuid()).append("_").append(imageInfo.getFileName());
				
				String thumbPath = Paths.get(imageInfo.getUploadPath(), sb.toString())
						.toString();
				
				ImageFilePathList.add(thumbPath);
				ImageFilePathList.add(thumbPath.replaceAll("t_", ""));
				
				sb.setLength(0);
				
			});
			
			return ImageFilePathList;
		}
		return new ArrayList<>();
	}
}
