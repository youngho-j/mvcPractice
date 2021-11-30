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
	public List<BookVO> goodsGetList(PageInfo pageInfo) {
		log.info("상품 목록 출력");
		return bookMapper.goodsGetList(pageInfo);
	}

	@Override
	public int goodsGetTotal(PageInfo pageInfo) {
		log.info("상품 총 개수 출력");
		return bookMapper.goodsGetTotal(pageInfo);
	}
	
}
