package com.spring.board.form;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BoardFormTest {

	SimpleDateFormat sdf;
	
	@Before
	public void setUp() throws Exception {
		sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	}

	@Test
	public void 롬복_기능_테스트() throws Exception{
		
		//given
	    String board_content = "내용";
	    Date ins_date = sdf.parse("2021-07-18 17:00:00");
	    Date upd_date = sdf.parse("2021-07-18 18:00:00");
	    String search_type = "S";
	    int total_list_conut = 50;
	    
	    //when
	    BoardForm boardForm = new BoardForm();
	    boardForm.setBoard_content(board_content);
	    boardForm.setIns_date(ins_date);
	    boardForm.setUpd_date(upd_date);
	    boardForm.setSearch_type(search_type);
	    boardForm.setTotal_list_count(total_list_conut);
	    
	    //then(일부 테스트)
	    assertThat(boardForm.getBoard_content()).isEqualTo(board_content);
	    assertThat(boardForm.getIns_date()).isCloseTo(ins_date, 0);
	    assertThat(boardForm.getUpd_date()).isCloseTo(ins_date, 3600000);
	    assertThat(boardForm.getSearch_type()).isEqualTo(search_type);
	    assertThat(boardForm.getTotal_list_count()).isEqualTo(total_list_conut);
	}

}
