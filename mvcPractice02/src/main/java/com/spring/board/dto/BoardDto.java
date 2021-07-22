package com.spring.board.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date ins_date;
    String upd_user_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date upd_date;
    // 데이터 수정, 삭제, 입력시 사용할 변수 
    String result;
    // 상세 정보로 넘어갈때 필요한 변수
    String searchType;
}
