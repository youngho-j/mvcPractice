package com.spring.board.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDtoTest {
	SimpleDateFormat sdf;
	
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

	@Test
	public void 롬복_기능_테스트() throws Exception{
		
		//given
		int board_seq = 1;
	    int board_re_ref = 0;
	    int board_re_lev = 0;
	    int board_re_seq = 0;
	    String board_writer = "작성자";
	    String board_subject = "제목";
	    String board_content = "내용";
	    int board_hits = 0;
	    String del_yn = "N";
	    String ins_user_id = "test01";
	    Date ins_date = sdf.parse("2021-07-18 17:00:00");
	    String upd_user_id = "test01";
	    Date upd_date = sdf.parse("2021-07-18 18:00:00");
	    String result = "Y";
	    String searchType = "S";
	    //when
	    BoardDto boardDto = 
	    		new BoardDto(board_seq, board_re_ref, board_re_lev, board_re_seq,
	    				board_writer, board_subject, board_content, board_hits,
	    				del_yn, ins_user_id, ins_date, upd_user_id, upd_date, result, searchType);
	    
	    //then(일부 테스트)
	    assertThat(boardDto.getBoard_content()).isEqualTo(board_content);
	    assertThat(boardDto.ins_date).isCloseTo(ins_date, 0);
	    assertThat(boardDto.upd_date).isCloseTo(ins_date, 3600000);
	    assertThat(boardDto.result).isEqualTo(result);
	    assertThat(boardDto.searchType).isEqualTo(searchType);
	}
}
