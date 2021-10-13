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
	
	public PageInfo(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.viewPerPage = amount;
		this.skip = (pageNum - 1) * amount;
	}
	
	public PageInfo () {
		this(1, 10);
	}
	
	// 검색 타입 삼항 연산
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
}
