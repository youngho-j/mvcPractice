package com.spring.shop.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.spring.shop.mapper.BookMapper;
import com.spring.shop.mapper.FileMapper;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService{
	
	private BookMapper bookMapper;
	
	private FileMapper fileMapper;
	
	@Autowired
	public BookServiceImpl(BookMapper bookMapper, FileMapper fileMapper) {
		this.bookMapper = bookMapper;
		this.fileMapper = fileMapper;
	}
	
	@Transactional
	@Override
	public int goodsEnroll(BookVO bookVO) throws Exception {
		log.info("상품 등록 service 실행");

		int enrollResult = 0;
		
		int imageEnrollResult = 0;
		
		ImageInfoVO imageInfo = null;
			
		int bookEnrollResult = bookMapper.bookEnroll(bookVO);
			
		// 상품 정보 등록 실패인 경우
		if(bookEnrollResult < 1) {
			log.info("상품 정보 등록실패!");
			return 0;
		}
			
		// 이미지 없는 상품 등록
		if(bookEnrollResult == 1 && CollectionUtils.isEmpty(bookVO.getImagesList())) {
			log.info("이미지 없는 상품 정보 등록");
			return bookEnrollResult;
		}
			
		// 해당 상품 이미지 등록
		for(int i = 0 ; i < bookVO.getImagesList().size() ; i++) {
			imageInfo = new ImageInfoVO.Builder()
					.bookId(bookVO.getBookId())
					.uploadPath(bookVO.getImagesList().get(i).getUploadPath())
					.uuid(bookVO.getImagesList().get(i).getUuid())
					.fileName(bookVO.getImagesList().get(i).getFileName())
					.build();
				
			imageEnrollResult += fileMapper.goodsImgEnroll(imageInfo);
		}
			
		enrollResult = bookEnrollResult + imageEnrollResult;
			
		log.info("이미지 있는 상품 정보 등록");
		return enrollResult;
		
	}

	@Override
	public BookVO goodsDetail(int bookId) throws Exception {
		log.info("상품 상세정보 service 실행");
		return bookMapper.goodsDetail(bookId);
	}
	
	@Transactional
	@Override
	public int goodsModify(BookVO bookVO) throws Exception {
		log.info("상품 수정 service 실행");
		int updateResult = 0;
		int imageUpdateResult = 0;
		
		ImageInfoVO imageInfo = null;
		
		// 상품 정보 수정
		int bookUpdateResult = bookMapper.goodsModify(bookVO);
		
		// 상품 정보 수정 실패인 경우
		if(bookUpdateResult < 1) {
			log.info("상품 정보 등록실패!");
			return 0;
		}
		
		// 이미지 없는 상품 수정
		if(bookUpdateResult == 1 && bookVO.getImagesList() == null || bookVO.getImagesList().size() <= 0) {
			log.info("이미지 없는 상품 정보 수정");
			return bookUpdateResult;
		}
		
		// 기존 이미지 존재하는 경우
		log.info("이미지 있는 상품 정보 등록");
		log.info("기존 등록 이미지 삭제");
		fileMapper.goodsImgDelete(bookVO.getBookId());
		
		log.info("새로운 이미지 등록");
		for(int i = 0 ; i < bookVO.getImagesList().size() ; i++) {
			imageInfo = new ImageInfoVO.Builder()
					.bookId(bookVO.getBookId())
					.uploadPath(bookVO.getImagesList().get(i).getUploadPath())
					.uuid(bookVO.getImagesList().get(i).getUuid())
					.fileName(bookVO.getImagesList().get(i).getFileName())
					.build();
					
			imageUpdateResult += fileMapper.goodsImgEnroll(imageInfo);
		}
		
		updateResult = bookUpdateResult + imageUpdateResult;
		
		return updateResult;
	}
	
	@Transactional
	@Override
	public List<ImageInfoVO> goodsDelete(int bookId) throws Exception {
		log.info("상품 정보 삭제 service 실행");
		List<ImageInfoVO> goodsImgList = fileMapper.getImageList(bookId);
		
		if(CollectionUtils.isEmpty(goodsImgList)) {
			log.info("등록된 이미지 정보 없음");
			bookMapper.goodsDelete(bookId);
			return new ArrayList<>();
		}
		
		fileMapper.goodsImgDelete(bookId);
		bookMapper.goodsDelete(bookId);
		
		return goodsImgList;
	}

	@Override
	public void deleteAll() {
		fileMapper.deleteAll();
		bookMapper.deleteAll();
	}

	@Override
	public int getCount() {
		
		int bookCount = bookMapper.getCount();
		int fileCount = fileMapper.getCount();
		
		return bookCount + fileCount;
	}
	
	@Override
	public int getLastPK() {
		return bookMapper.getLastPK();
	}
}
