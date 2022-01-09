package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

public interface AuthorSearchMapper {
	
	// 작가 목록
	public List<AuthorVO> authorGetList(PageInfo paging);
	
	// 등록된 작가 인원 수
	public int authorGetTotal(PageInfo paging);
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
	
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
	
	// 마지막으로 등록한 작가 정보(테스트용)
	public int getLastPK();
}
