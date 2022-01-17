package com.spring.shop.service;

import com.spring.shop.vo.AuthorVO;

public interface AuthorService extends TestMethodService{

	// 작가 등록
	public int authorEnroll(AuthorVO authorVO) throws Exception;
	
	// 작가 상세정보
	public AuthorVO authorGetDetail(int authorId) throws Exception;
	
	// 작가 정보 수정
	public int authorModify(AuthorVO authorVO) throws Exception;
	
	// 작가 정보 삭제
	public int authorDelete(int authorId) throws Exception;
}
