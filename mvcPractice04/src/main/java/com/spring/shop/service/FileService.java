package com.spring.shop.service;

import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileService {
	
	// 이미지 정보 출력
	public List<ImageInfoVO> getImageList(int bookId) throws Exception;
}
