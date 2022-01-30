package com.spring.shop.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.shop.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckScheduler {
	
	FileService fileService;
	
	@Autowired
	public FileCheckScheduler(FileService fileService) {
		this.fileService = fileService;
	}

	@Scheduled(cron = "0 0 1 1/1 * ?")
	public void checkNeedlessFiles() throws Exception {
		log.warn("불필요 이미지 파일 제거 스케쥴러 실행");
		
		List<String> dbImageList = fileService.getImageFileList();
		
		List<String> folderImageList = fileService.getImageFileListInFolder();
		
		boolean result = 
				fileService.deleteUnknownFiles(dbImageList, folderImageList);
		
		if(!result) {
			log.info("파일 제거 실패");
			return;
		}
		log.info("파일 제거 성공");
	}
}
