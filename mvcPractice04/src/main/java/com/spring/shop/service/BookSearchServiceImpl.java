package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.BookSearchMapper;
import com.spring.shop.mapper.FileMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookSearchServiceImpl implements BookSearchService{
	
	private BookSearchMapper bookSearchMapper;
	
	private FileMapper fileMapper;
	
	@Autowired
	public BookSearchServiceImpl(BookSearchMapper bookSearchMapper, FileMapper fileMapper) {
		this.bookSearchMapper = bookSearchMapper;
		this.fileMapper = fileMapper;
	}

	@Override
	public List<BookVO> getGoodsList(PageInfo pageInfo) {
		log.info("[메인 검색 페이지] 상품 목록 출력");
		// 검색 조건 확인
		String[] typeArr = pageInfo.getTypeArr();
		
		for(String str : typeArr) {
			// 검색 조건 확인 후 작가 검색일 경우 
			if(str.equals("A")) {
				List<String> authorIdList = bookSearchMapper.getAuthorIdList(pageInfo.getKeyword());
				pageInfo.setAuthorIdList(authorIdList);
			}
		}
		
		List<BookVO> goodsList = bookSearchMapper.getGoodsList(pageInfo);
		
		// 해당 상품의 이미지 추가
		goodsList.forEach(bookInfo -> {
			try {
				List<ImageInfoVO> imageList = fileMapper.getImageList(bookInfo.getBookId());
				bookInfo.setImagesList(imageList);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		});
		
		return goodsList;
	}

	@Override
	public int getGoodsTotal(PageInfo pageInfo) {
		log.info("[메인 검색 페이지] 상품 총 개수 출력");
		
		// 검색 조건 확인
		String[] typeArr = pageInfo.getTypeArr();
		
		for(String str : typeArr) {
			// 검색 조건 확인 후 작가 검색일 경우 
			if(str.equals("A")) {
				List<String> authorIdList = bookSearchMapper.getAuthorIdList(pageInfo.getKeyword());
				pageInfo.setAuthorIdList(authorIdList);
			}
		}
		
		return bookSearchMapper.getGoodsTotal(pageInfo);
	}

	@Override
	public List<BookVO> adminPageGoodsList(PageInfo pageInfo) {
		log.info("[관리자 상품 페이지] 상품 목록");
		return bookSearchMapper.adminPageGoodsList(pageInfo);
	}

	@Override
	public int adminPageGoodsTotal(PageInfo pageInfo) {
		log.info("[관리자 상품 페이지] 상품 총 개수");
		return bookSearchMapper.adminPageGoodsTotal(pageInfo);
	}

	@Override
	public void deleteAll() {
		bookSearchMapper.deleteAll();
	}

	@Override
	public int getCount() {
		return bookSearchMapper.getCount();
	}

	@Override
	public int getLastPK() {
		return bookSearchMapper.getLastPK();
	}
}
