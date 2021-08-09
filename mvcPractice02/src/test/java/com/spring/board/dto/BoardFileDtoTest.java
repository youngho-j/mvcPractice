package com.spring.board.dto;

import static org.assertj.core.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BoardFileDtoTest {
	
	SimpleDateFormat sdf;
	
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}
	
	@Test
	public void 롬복_기능_테스트() throws Exception {
		
		int board_seq = 35;
		int file_num = 1;
		String file_name_key = "asd12sf3.png";
		String file_name = "사진1.png";
		Date ins_date = sdf.parse("2021-08-09 10:00:00");
	    Date upd_date = sdf.parse("2021-08-09 11:00:00");
	    
	    BoardFileDto boardFileDto = new BoardFileDto();
	    
	    boardFileDto.setBoard_seq(board_seq);
	    boardFileDto.setFile_num(file_num);
	    boardFileDto.setFile_name_key(file_name_key);
	    boardFileDto.setFile_name(file_name);
	    boardFileDto.setIns_date(ins_date);
	    boardFileDto.setUpd_date(upd_date);
	    
	    assertThat(boardFileDto.getBoard_seq()).isEqualTo(board_seq);
	    assertThat(boardFileDto.getFile_num()).isEqualTo(file_num);
	    assertThat(boardFileDto.getFile_name_key()).isEqualTo(file_name_key);
	    assertThat(boardFileDto.getFile_name()).isEqualTo(file_name);
	    assertThat(boardFileDto.getIns_date()).isCloseTo(ins_date, 0);
	    assertThat(boardFileDto.getUpd_date()).isCloseTo(ins_date, 3600000);
	    
	}

}
