package com.spring.shop.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.FileMapper;
import com.spring.shop.util.PathManager;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public List<ImageInfoVO> getImageList(int bookId) throws Exception {
		log.info("이미지 정보 출력 실행");
		return fileMapper.getImageList(bookId);
	}

	@Override
	public List<String> getImageFileList() throws Exception {
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
		return null;
	}
	
	@Override
	public List<String> getImageFileListInFolder() throws Exception {
		// 하루전 날짜의 폴더 경로를 가진 파일 객체 생성
		File folderPath = Paths.get("H:\\mvcPractice04upload", new PathManager().getTheDayBeforePath()).toFile();
		
		// 경로 안에 있는 모든 파일 정보를 배열에 담아 리턴
		File[] fileList = folderPath.listFiles();
		
		// 경로내 파일이 존재하는 경우  
		if(fileList.length != 0) {
			
			// 배열 리스트로 변환
			List<String> removeFileList = new ArrayList<>();
			
			for(File file : fileList) {
				removeFileList.add(file.toPath().toString());
			}
			
			return removeFileList;						
		}
		
		return null;
		
	}
	
	@Override
	public boolean deleteUnknownFiles(List<String> dbImageList, List<String> folderImageList) {
		List<String> targetFileList = new ArrayList<String>();
		try {
			if(dbImageList == null && folderImageList == null) {
				log.info("DB, Folder 저장된 이미지 없음");
				return true;
			}
			
			if(dbImageList == null && folderImageList != null) {
				log.info("Folder 저장된 이미지 삭제");
				
				for(String file : folderImageList) {
					File deleteFile = Paths.get(file).toFile();
					
					log.info("DB에 저장되어 있지 않은 파일 삭제 [{}]", deleteFile.toPath());
					deleteFile.delete();
				}
				
				return true;
			}
			
			if(dbImageList != null && folderImageList != null) {
				log.info("DB와 비교 후 Folder 저장된 이미지 삭제");
				
				// 폴더 내 이미지 목록과 DB 저장 이미지 목록을 비교 후 겹치는 목록을 제외한 나머지 목록 
				targetFileList = folderImageList.stream()
						.filter(x -> !dbImageList.contains(x))
						.collect(Collectors.toList());
				
				// 파일 삭제
				for(String file : targetFileList) {
					File deleteFile = Paths.get(file).toFile();
					
					log.info("DB에 저장되어 있지 않은 파일 삭제 [{}]", deleteFile.toPath());
					deleteFile.delete();
				}
				
				return true;
			}
			
		} catch (Exception e) {
			log.info("오류");
			e.printStackTrace();
			return false;		
		} 
		
		return false;
	}

	@Override
	public boolean deleteImageFiles(List<ImageInfoVO> fileList)  {
		log.info("이미지 파일들 삭제");
		if(!fileList.isEmpty()) {
			List<File> deleteFileList = new ArrayList<File>();
			
			fileList.forEach(info -> {
				
				File imageFile = new File(info.getUploadPath(), info.getUuid() + "_" + info.getFileName());
				File thumbFile = new File(info.getUploadPath(), "t_" + info.getUuid() + "_" + info.getFileName());
				
				deleteFileList.add(imageFile);
				deleteFileList.add(thumbFile);
				
			});
			
			// 삭제
			for(File file : deleteFileList) {
				try {
					log.info(file.getAbsolutePath() + " 경로의 파일 삭제");
					file.delete();
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				
			}
			
			return true;
		}
		return true;
	}
}
