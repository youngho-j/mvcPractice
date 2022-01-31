package com.spring.shop.service;

import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileService {
	
	// 이미지 정보 출력
	public List<ImageInfoVO> getImageList(int bookId) throws Exception;
	
	// DB 저장 이미지 파일 목록 출력
	public List<String> getImageFileList() throws Exception;
	
	// 폴더 저장 이미지 파일 목록 출력
	public List<String> getImageFileListInFolder() throws Exception;
	
	// 파일 비교 후 삭제
	public boolean deleteUnknownFiles(List<String> dbImageList, List<String> folderImageList);
	
	// DB 저장 이미지 파일 삭제
	public boolean deleteImageFiles(List<ImageInfoVO> fileList);
}
