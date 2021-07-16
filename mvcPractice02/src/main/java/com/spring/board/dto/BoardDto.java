package com.spring.board.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	
	int board_seq;
    int board_re_ref;
    int board_re_lev;
    int board_re_seq;
    String board_writer;
    String board_subject;
    String board_content;
    int board_hits;
    String del_yn;
    String ins_user_id;
    Date ins_date;
    String upd_user_id;
    Date upd_date;

}
