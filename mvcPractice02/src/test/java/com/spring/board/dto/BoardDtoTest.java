package com.spring.board.dto;

import java.text.SimpleDateFormat;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardDtoTest {
	SimpleDateFormat sdf;
	
	@Test
	public void 롬복_기능_테스트() throws Exception{
		
		//given
	    String board_content = "내용";
	    String ins_date = "2021-07-18 17:00:00";
	    String upd_date = "2021-07-18 18:00:00";
	    String result = "Y";
	    String pagination = "함수명";
	    
	    //when
	    BoardDto boardDto = new BoardDto();
	    boardDto.setBoard_content(board_content);
	    boardDto.setIns_date(ins_date);
	    boardDto.setUpd_date(upd_date);
	    boardDto.setResult(result);
	    boardDto.setPagination(pagination);
	    
	    //then(일부 테스트)
	    assertThat(boardDto.getBoard_content()).isEqualTo(board_content);
	    assertThat(boardDto.getIns_date()).isEqualTo(ins_date);
	    assertThat(boardDto.getUpd_date()).isEqualTo(upd_date);
	    assertThat(boardDto.getResult()).isEqualTo(result);
	    assertThat(boardDto.getPagination()).isEqualTo(pagination);
	}
}
