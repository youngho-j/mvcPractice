package com.spring.shop.service;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileService {
	
	// 이미지 정보 출력
	public List<ImageInfoVO> getImageList(int bookId) throws Exception;
	
	// 이미지 파일 경로 출력
	public List<Path> getImageFilePathList() throws Exception;
	
	public File[] getImageFileListInFolder() throws Exception;
}
