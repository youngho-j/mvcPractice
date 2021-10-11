package com.spring.shop.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingManager {
	
	// 현재 페이지 번호
	private int pageNum;
	
	// 한 페이지 당 표시되는 글의 수 
	private int amount;
	
	// 목록 이동시 넘어가는 글의 수
	private int skip;
	
	// 검색 타입
	private String type;
	
	// 검색어
	private String keyword;
	
	public PagingManager(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.skip = (pageNum - 1) * amount;
	}
	
	public PagingManager () {
		this(1, 10);
	}
	
	// 검색 타입 삼항 연산
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
	}
}
