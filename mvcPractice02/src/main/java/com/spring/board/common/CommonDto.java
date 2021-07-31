package com.spring.board.common;

import lombok.Getter;
import lombok.Setter;

/*
 * limit = 가져올 row 수
 * offset = 몇번째 row부터 가져올지 결정
 * pagination = 페이징 결과값
 * 
*/
@Getter
@Setter
public class CommonDto {
	int limit;
	int offset;
	String pagination;
}
