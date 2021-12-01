package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.BookVO;

public interface BookMapper {
	
	// 상품 목록
	public List<BookVO> goodsGetList(PageInfo pageInfo);
	
	// 상품 전체 갯수
	public int getGoodsTotal(PageInfo pageInfo);
	
	// 작가 id 목록
	public String[] getAuthorIdList(String keyword);
}
