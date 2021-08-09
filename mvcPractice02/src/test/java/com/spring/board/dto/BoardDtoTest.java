package com.spring.board.dto;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

public class BoardDtoTest {
	
	
	@Test
	public void 롬복_기능_테스트() throws Exception{

		BoardFileDto file1 = new BoardFileDto();
		BoardFileDto file2 = new BoardFileDto();
		BoardFileDto file3 = new BoardFileDto();
		
		//given
	    String board_content = "내용";
	    String ins_date = "2021-07-18 17:00:00";
	    String upd_date = "2021-07-18 18:00:00";
	    String result = "Y";
	    String pagination = "함수명";
	    int board_parent_seq = 1;
	    
	    List<BoardFileDto> list = new ArrayList<BoardFileDto>();
	    list.add(file1);
	    list.add(file2);
	    list.add(file3);
	    
	    //when
	    BoardDto boardDto = new BoardDto();
	    boardDto.setBoard_content(board_content);
	    boardDto.setIns_date(ins_date);
	    boardDto.setUpd_date(upd_date);
	    boardDto.setResult(result);
	    boardDto.setPagination(pagination);
	    boardDto.setBoard_parent_seq(board_parent_seq);
	    boardDto.setFiles(list);
	    
	    //then(일부 테스트)
	    assertThat(boardDto.getBoard_content()).isEqualTo(board_content);
	    assertThat(boardDto.getIns_date()).isEqualTo(ins_date);
	    assertThat(boardDto.getUpd_date()).isEqualTo(upd_date);
	    assertThat(boardDto.getResult()).isEqualTo(result);
	    assertThat(boardDto.getPagination()).isEqualTo(pagination);
	    assertThat(boardDto.getBoard_parent_seq()).isEqualTo(board_parent_seq);
	    assertThat(boardDto.getFiles().size()).isEqualTo(3);
	}
}
