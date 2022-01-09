package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

public interface AuthorSearchService {

	// 작가 목록
	public List<AuthorVO> authorGetList(PageInfo paging) throws Exception;
	
	// 등록된 작가 수
	public int authorGetTotal(PageInfo paging) throws Exception;
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
		
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
		
	// 마지막으로 등록한 작가 정보(테스트용)
	public int getLastPK();
}
