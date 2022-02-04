package com.spring.shop.util;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckServiceImpl implements FileCheckService{

	@Override
	public List<String> getListOfFilesInFolder() throws Exception {
		
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
		
		return new ArrayList<>();
	}

	@Override
	public List<String> getUnknownFiles(List<String> dbImageList, List<String> folderImageList) throws Exception {
		log.info("FileCheckScheduler 불필요한 이미지 파일 제거");
		
		List<String> targetFileList = new ArrayList<String>();
		
		if(dbImageList.isEmpty() && folderImageList.isEmpty()) {
			log.info("DB, Folder 저장된 이미지 없음");
			return new ArrayList<>();
		}
			
		if(dbImageList.isEmpty() && !folderImageList.isEmpty()) {
			log.info("Folder 저장된 이미지 목록 출력");
			return folderImageList;
		}
			
		if(!dbImageList.isEmpty() && !folderImageList.isEmpty()) {
			log.info("DB와 Folder 저장된 이미지 목록 비교");
			
			/* 폴더 내 이미지 목록과 DB 저장 이미지 목록을 비교 후 
			   겹치는 목록을 제외한 나머지 목록
			*/ 
			targetFileList = folderImageList.stream()
					.filter(x -> !dbImageList.contains(x))
					.collect(Collectors.toList());
				
			return targetFileList;
		}
		
		return new ArrayList<>();
	}

	@Override
	public void deleteFilesInFolder(List<String> deleteImageList) throws Exception {
		log.info("폴더 내 불필요한 파일 삭제");
		// collection이 비어있을 경우 빈 스트림 리턴
		Stream<String> fileList = 
				Optional.ofNullable(deleteImageList)
				.map(Collection::stream).orElseGet(Stream::empty);
		
		// stream은 한번만 완료될 수 있음
		fileList
		.collect(Collectors.toList())
		.forEach(file -> {
			
			log.info("DB에 저장되어 있지 않은 파일 삭제 [{}]", Paths.get(file).toString());
			
			Paths.get(file).toFile().delete();
		});
	}
	
}
