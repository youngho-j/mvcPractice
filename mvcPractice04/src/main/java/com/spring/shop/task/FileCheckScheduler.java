package com.spring.shop.task;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.shop.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileCheckScheduler {
	
	@Autowired
	FileService fileService;
	
	@Scheduled(cron = "0 0 1 1/1 * ?")
	public void checkFiles() throws Exception {
		log.warn("불필요 이미지 파일 제거 스케쥴러 실행");
		
		List<Path> dbImagePath = fileService.getImageFilePathList();
		
		List<File> folderImageFile = fileService.getImageFileListInFolder();
		
		boolean result = 
				fileService.thinOutFilesInFolder(dbImagePath, folderImageFile);
		
		if(!result) {
			log.info("파일 제거 실패");
		}
	}
}
