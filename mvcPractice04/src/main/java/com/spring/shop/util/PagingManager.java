package com.spring.shop.util;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingManager {
	
	// 전체 행 개수
	private int totalCount;
	
	// 페이지 시작 번호
	private int pageStartNum;
	
	// 페이지 끝 번호 
	private int pageEndNum;
	
	// 이전, 다음 버튼 존재 유무
	private boolean prev;
	private boolean next;
	
	// 페이지에 대한 정보가 담긴 객체(현재 페이지 번호, 페이지당 표시되는 수, 검색 키워드 및 타입
	private PageInfo pageInfo;
	
	public PagingManager(PageInfo pageInfo, int totalCount) {
		
		this.totalCount = totalCount;
		
		this.pageInfo = pageInfo;
		
		this.pageEndNum = (int)(Math.ceil(pageInfo.getPageNum() / 10.0)) * 10;
		
		this.pageStartNum = this.pageEndNum - 9;
		
		// 마지막 페이지 번호
		int lastPage = (int)(Math.ceil((totalCount * 1.0) / pageInfo.getViewPerPage()));
		
		if(lastPage < pageEndNum) {
			this.pageEndNum = lastPage;
		}
		
		this.prev = this.pageStartNum > 1;
		
		this.next = this.pageEndNum < lastPage;
	}
}
