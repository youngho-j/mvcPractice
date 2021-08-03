package com.spring.board.dto;

import com.spring.board.common.CommonDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto extends CommonDto {
	
	int board_seq;
	int board_parent_seq; // 부모글 번호
    int board_re_ref;
    int board_re_lev;
    int board_re_seq;
    String board_writer;
    String board_subject;
    String board_content;
    int board_hits;
    String del_yn;
    String ins_user_id;
    String ins_date;
    String upd_user_id;
    String upd_date;
    // 데이터 수정, 삭제, 입력시 사용할 변수 
    String result;
}
