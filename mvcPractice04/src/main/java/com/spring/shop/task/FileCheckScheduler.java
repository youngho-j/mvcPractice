package com.spring.shop.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.shop.service.FileService;
import com.spring.shop.util.FileCheckService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckScheduler {
	
	private FileService fileService;
	
	private FileCheckService fileCheckService;
	
	@Autowired
	public FileCheckScheduler(FileService fileService, FileCheckService fileCheckService) {
		this.fileService = fileService;
		this.fileCheckService = fileCheckService;
	}

	@Scheduled(cron = "0 0 1 1/1 * ?")
	public void checkNeedlessFiles() throws Exception {
		log.info("불필요 이미지 파일 제거 스케쥴러 실행");
		
		List<String> dbImageList = fileService.getTheDayBeforeListOfImgFiles();
		
		List<String> folderImageList = fileCheckService.getListOfFilesInFolder();
		
		List<String> deleteImageList = fileCheckService.getUnknownFiles(dbImageList, folderImageList);
		
		fileCheckService.deleteFilesInFolder(deleteImageList);
	}
}
