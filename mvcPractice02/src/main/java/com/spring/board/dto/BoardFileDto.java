package com.spring.board.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardFileDto {
	
	int board_seq;
    int file_num;
    String file_name_key;
    String file_name;
    String file_path;
    String file_size;
    String remark;
    String del_yn;
    String ins_user_id;
    Date ins_date;
    String upd_user_id;
    Date upd_date;
    
}
