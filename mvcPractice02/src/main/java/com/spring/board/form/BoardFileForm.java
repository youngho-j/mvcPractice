package com.spring.board.form;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFileForm {
	
	int board_seq;
	int file_num;
	String file_name_key;
	String file_name;
	String file_path;
	String file_size;
	String remark;
	String del_yn;
	String ins_user_id;
	Date int_date;
	String upd_user_id;
	Date upd_date;
	
}
