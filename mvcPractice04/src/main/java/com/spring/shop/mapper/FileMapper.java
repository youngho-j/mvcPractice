package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.vo.ImageInfoVO;

public interface FileMapper {
	
	// 상품 이미지 등록
	public int goodsImgEnroll(ImageInfoVO imageInfo) throws Exception;
		
	// 상품 이미지 정보 삭제
	public int goodsImgDelete(int bookId) throws Exception;
	
	// 상품 이미지 정보 목록 조회
	public List<ImageInfoVO> getImageList(int bookId);
		
	// 하루 전 이미지 정보 목록 출력
	public List<ImageInfoVO> getPreviousImageList();
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
			
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
	
	// 마지막으로 등록한 상품 정보(테스트용)
	public int getLastPK();
}
