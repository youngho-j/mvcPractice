package com.spring.shop.service;

import java.io.File;
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
		List<String> ImageFilePathList = new ArrayList<String>();
		
		List<ImageInfoVO> imageInfoList = fileMapper.getPreviousImageList();
		
		if(!imageInfoList.isEmpty()) {
			
			imageInfoList.forEach(imageInfo -> {
				
				ImageFilePathList.add(
						Paths.get(imageInfo.getUploadPath(), imageInfo.getUuid() + "_" + imageInfo.getFileName()).toString());
				ImageFilePathList.add(
						Paths.get(imageInfo.getUploadPath(), "t_" + imageInfo.getUuid() + "_" + imageInfo.getFileName()).toString());
				
			});
			
			return ImageFilePathList;
		}
		return new ArrayList<>();
	}
// 사용되는 곳이 없어 일단 주석 처리
//	@Override
//	public boolean deleteImageFiles(List<ImageInfoVO> fileList)  {
//		log.info("이미지 파일들 삭제");
//		if(!fileList.isEmpty()) {
//			List<File> deleteFileList = new ArrayList<File>();
//			
//			fileList.forEach(info -> {
//				
//				File imageFile = new File(info.getUploadPath(), info.getUuid() + "_" + info.getFileName());
//				File thumbFile = new File(info.getUploadPath(), "t_" + info.getUuid() + "_" + info.getFileName());
//				
//				deleteFileList.add(imageFile);
//				deleteFileList.add(thumbFile);
//				
//			});
//			
//			// 삭제
//			for(File file : deleteFileList) {
//				try {
//					log.info(file.getAbsolutePath() + " 경로의 파일 삭제");
//					file.delete();
//				} catch (Exception e) {
//					e.printStackTrace();
//					return false;
//				}
//				
//			}
//			
//			return true;
//		}
//		return true;
//	}
}
