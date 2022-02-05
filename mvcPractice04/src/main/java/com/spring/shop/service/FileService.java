package com.spring.shop.service;

import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileService {
	
	// 이미지 정보 출력
	public List<ImageInfoVO> getImageList(int bookId) throws Exception;
	
	// DB 저장 이미지 파일 목록 출력
	public List<String> getImageFileList() throws Exception;
	
	// DB 저장 이미지 파일 삭제
	public boolean deleteImageFiles(List<ImageInfoVO> fileList);
}
