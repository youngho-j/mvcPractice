package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileMapper {
	
	// 이미지 정보 출력 
	public List<ImageInfoVO> getImageList(int bookId) throws Exception;
	
	// 하루 전 이미지 정보 목록 출력
	public List<ImageInfoVO> getImageListCheck() throws Exception;
}
