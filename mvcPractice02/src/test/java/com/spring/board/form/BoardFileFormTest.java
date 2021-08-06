package com.spring.board.form;

import static org.assertj.core.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BoardFileFormTest {
	
	SimpleDateFormat sdf;
	
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}
	
	@Test
	public void 롬복_기능_테스트() throws Exception {
		
		int board_seq = 35;
		int file_num = 1;
		String file_name = "사진1.jpg";
		Date ins_date = sdf.parse("2021-08-06 14:00:00");
		String ins_user_id = "test1";
	    Date upd_date = sdf.parse("2021-08-06 15:00:00");
	    String upd_user_id = "testUp";
	    
	    BoardFileForm boardFileForm = new BoardFileForm();
	    boardFileForm.setBoard_seq(board_seq);
	    boardFileForm.setFile_num(file_num);
	    boardFileForm.setFile_name(file_name);
	    boardFileForm.setInt_date(ins_date);
	    boardFileForm.setIns_user_id(ins_user_id);
	    boardFileForm.setUpd_date(upd_date);
	    boardFileForm.setUpd_user_id(upd_user_id);
	    
	    assertThat(boardFileForm.getBoard_seq()).isEqualTo(board_seq);
	    assertThat(boardFileForm.getInt_date()).isCloseTo(upd_date, 3600000);
	    assertThat(boardFileForm.getFile_name()).isEqualTo("사진1.jpg");
	    assertThat(boardFileForm.getIns_user_id()).isEqualTo(ins_user_id);
	}
}
