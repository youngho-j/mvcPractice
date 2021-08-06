package com.spring.board.form;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.board.common.CommonForm;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardForm extends CommonForm {
	
	List<MultipartFile> files;
	int board_seq;
	int board_parent_seq; // 부모글 번호
    int board_re_ref; // 그룹 번호
    int board_re_lev; // 답급 깊이
    int board_re_seq; // 답글 순서(번호)
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
