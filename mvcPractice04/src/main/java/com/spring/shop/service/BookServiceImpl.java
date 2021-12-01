package com.spring.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.shop.mapper.BookMapper;
import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<BookVO> getGoodsList(PageInfo pageInfo) {
		log.info("상품 목록 출력");
		// 검색 조건 확인
		String[] typeArr = pageInfo.getTypeArr();
		
		for(String str : typeArr) {
			// 검색 조건 확인 후 작가 검색일 경우 
			if(str.equals("A")) {
				String[] authorList = bookMapper.getAuthorIdList(pageInfo.getKeyword());
				pageInfo.setAuthorList(authorList);
			}
		}
		
		return bookMapper.goodsGetList(pageInfo);
	}

	@Override
	public int getGoodsTotal(PageInfo pageInfo) {
		log.info("상품 총 개수 출력");
		return bookMapper.getGoodsTotal(pageInfo);
	}
	
}
