package com.spring.shop.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
		
		if(!imageInfoList.isEmpty()) {
			
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
		return null;
	}
	
	@Override
	public List<File> getImageFileListInFolder() throws Exception {
		// 폴더 경로를 가진 파일 객체 생성
		File folderPath = Paths.get("H:\\mvcPractice04upload", getFolderDatePath()).toFile();
		
		// 경로 안에 있는 모든 파일 정보를 배열에 담아 리턴
		File[] fileList = folderPath.listFiles();
		
		// 경로내 파일이 존재하는 경우  
		if(fileList.length != 0) {
			// 배열 리스트로 변환
			List<File> removeFileList = new ArrayList<>(Arrays.asList(fileList));
			
			return removeFileList;						
		}
		
		return null;
		
	}
	
	@Override
	public boolean thinOutFilesInFolder(List<Path> dbImageList, List<File> folderImageList) {
		try {
			if(dbImageList == null && folderImageList == null) {
				log.info("DB, Folder 저장된 이미지 없음");
				return true;
			}
			
			if(dbImageList == null && folderImageList != null) {
				log.info("Folder 저장된 이미지 삭제");
				
				for(File file : folderImageList) {
					file.delete();
				}
				
				return true;
			}
			
			if(dbImageList != null && folderImageList != null) {
				log.info("DB와 비교 후 Folder 저장된 이미지 삭제");
				
				File[] checkList = folderImageList.toArray(new File[folderImageList.size()]);
				
				for(File folderImageFile : checkList) {
					for(Path dbImagePath : dbImageList) {
						if(folderImageFile.toPath().equals(dbImagePath)) {
							folderImageList.remove(folderImageFile);
						}
					}
				}
				
				// 파일 삭제
				for(File file : folderImageList) {
					file.delete();
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
