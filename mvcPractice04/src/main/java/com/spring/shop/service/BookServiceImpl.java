package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.BookMapper;
import com.spring.shop.mapper.FileMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.ImageInfoVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private FileMapper fileMapper;
	
	@Override
	public List<BookVO> getGoodsList(PageInfo pageInfo) throws Exception {
		log.info("상품 목록 출력");
		// 검색 조건 확인
		String[] typeArr = pageInfo.getTypeArr();
		
		for(String str : typeArr) {
			// 검색 조건 확인 후 작가 검색일 경우 
			if(str.equals("A")) {
				List<String> authorIdList = bookMapper.getAuthorIdList(pageInfo.getKeyword());
				pageInfo.setAuthorIdList(authorIdList);
			}
		}
		
		List<BookVO> goodsList = bookMapper.getGoodsList(pageInfo);
		
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
	public int getGoodsTotal(PageInfo pageInfo) throws Exception {
		log.info("상품 총 개수 출력");
		return bookMapper.getGoodsTotal(pageInfo);
	}
	
}
