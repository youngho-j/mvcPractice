package com.spring.board.form;

import java.util.Date;

import com.spring.board.common.CommonForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm extends CommonForm{
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
    // 상세 정보로 넘어갈때 필요한 변수
    String search_type;
}
