package com.spring.shop.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public int bookEnroll(BookVO bookVO) throws Exception {
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
		if(bookEnrollResult == 1 && bookVO.getImagesList() == null || bookVO.getImagesList().size() <= 0) {
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
		return bookMapper.goodsDetail(bookId);
	}

	@Override
	public int goodsModify(BookVO bookVO) {
		return 0;
	}
	
	@Transactional
	@Override
	public int goodsDelete(int bookId) throws Exception {
		int deleteResult = 0;
		
		List<ImageInfoVO> goodsImgList = fileMapper.getImageList(bookId);
		
		if(goodsImgList.size() > 0) {
			deleteResult += fileMapper.goodsImgDelete(bookId);
		}
		
		deleteResult += bookMapper.goodsDelete(bookId);
		
		return deleteResult;
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
