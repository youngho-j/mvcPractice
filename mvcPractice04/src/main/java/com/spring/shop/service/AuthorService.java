package com.spring.shop.service;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

public interface AuthorService {

	// 작가 등록
	public int authorEnroll(AuthorVO authorVO) throws Exception;
	
	// 작가 목록
	public List<AuthorVO> authorGetList(PageInfo paging) throws Exception;
	
	// 등록된 작가 수
	public int authorGetTotal(PageInfo paging) throws Exception;
	
	// 작가 상세정보
	public AuthorVO authorGetDetail(int authorId) throws Exception;
	
	// 작가 정보 수정
	public int authorModify(AuthorVO authorVO) throws Exception;
	
	// 작가 정보 삭제
	public int authorDelete(int authorId) throws Exception;
}
