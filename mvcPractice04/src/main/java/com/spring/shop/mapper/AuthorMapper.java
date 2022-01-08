package com.spring.shop.mapper;

import java.util.List;

import com.spring.shop.util.PageInfo;
import com.spring.shop.vo.AuthorVO;

public interface AuthorMapper {
	
	// 작가 등록
	public int authorEnroll(AuthorVO authorVO);
	
	// 작가 목록
	public List<AuthorVO> authorGetList(PageInfo paging);
	
	// 등록된 작가 인원 수
	public int authorGetTotal(PageInfo paging);

	// 작가 상세
	public AuthorVO authorGetDetail(int authorId);
	
	// 작가 정보 수정
	public int authorModify(AuthorVO authorVO);
	
	// 작가 정보 삭제
	public int authorDelete(int authorId);
	
	// 작가의 ID로 등록된 책의 수
	public int authorWrittenBook(int authorId);
	
	// 테이블 전체 삭제(테스트용)
	public void deleteAll();
	
	// 테이블 전체 삭제 검증(테스트용)
	public int getCount();
	
	// 마지막으로 등록한 작가 정보(테스트용)
	public int getLastPK();
}
