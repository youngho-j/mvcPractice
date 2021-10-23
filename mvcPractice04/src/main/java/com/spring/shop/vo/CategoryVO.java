package com.spring.shop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryVO {
	
	// 등급
	private int tier;
	
	// 카테고리 이름
	private String categoryName;
	
	// 카테고리 코드번호
	private String categoryCode;
	
	// 상위 카테고리
	private String parentCategory;
}
