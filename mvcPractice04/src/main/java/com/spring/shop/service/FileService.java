package com.spring.shop.service;

import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileService extends TestMethodService {
	
	// 상품 이미지 정보 출력
	public List<ImageInfoVO> getImageList(int bookId) throws Exception;
	
	// 하루 전 DB에 저장된 이미지 파일 목록 출력
	public List<String> getTheDayBeforeListOfImgFiles() throws Exception;
}
