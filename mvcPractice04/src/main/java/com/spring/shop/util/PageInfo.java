package com.spring.shop.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageInfo {
	
	// 현재 페이지 번호
	@Setter(AccessLevel.NONE)
	private int pageNum;
	
	// 한 페이지 당 표시되는 글의 수
	@Setter(AccessLevel.NONE)
	private int viewPerPage;
	
	// 목록 이동시 넘어가는 글의 수
	@Setter(AccessLevel.NONE)
	private int skip;
	
	// 검색 타입
	private String type;
	
	// 검색어
	private String keyword;
	
	// 작가 목록 - 검색시 비슷한 작가 아이디 존재 할 수 있으므로
	private String[] authorList;
	
	// 카테고리 코드
	private String categoryCode;
	
	public PageInfo(int pageNum, int viewPerPage) {
		this.pageNum = pageNum;
		this.viewPerPage = viewPerPage;
		this.skip = (pageNum - 1) * viewPerPage;
	}
	
	public PageInfo () {
		this(1, 10);
	}
	
	public void setViewPerPage(int viewPerPage) {
		this.viewPerPage = viewPerPage;
		this.skip = (this.pageNum - 1) * viewPerPage;
	}
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		this.skip = (pageNum - 1) * this.viewPerPage;
	}
	
	// 검색 타입 삼항 연산
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
}
