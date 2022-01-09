package com.spring.shop.service;

import com.spring.shop.vo.AuthorVO;

public interface AuthorService {

	// 작가 등록
	public int authorEnroll(AuthorVO authorVO) throws Exception;
	
	// 작가 상세정보
	public AuthorVO authorGetDetail(int authorId) throws Exception;
	
	// 작가 정보 수정
	public int authorModify(AuthorVO authorVO) throws Exception;
	
	// 작가 정보 삭제
	public int authorDelete(int authorId) throws Exception;
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
		
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
		
	// 마지막으로 등록한 작가 정보(테스트용)
	public int getLastPK();
}
