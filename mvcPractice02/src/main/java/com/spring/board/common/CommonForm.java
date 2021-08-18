package com.spring.board.common;

import lombok.Getter;
import lombok.Setter;

/*
 * funtion_name = 페이징 목록을 요청하는 자바스크립트 함수명
 * current_page_num = 현재 페이지 번호
 * count_per_list = 한 화면에 출력될 게시물 수
 * count_per_page = 한 화면에 출력될 페이지 수
 * total_list_count = 총 게시물 수
 * total_page_count = 총 페이지 수
 * limit = 가져올 row의 수
 * offset = 시작번호(몇 번째 row 부터 시작하는지)
 */

@Getter
@Setter
public class CommonForm {
	String function_name;
	int current_page_num;
    int count_per_list;
    int count_per_page;
    int total_list_count;
    int total_page_count;
    int limit;
    int offset;
}
