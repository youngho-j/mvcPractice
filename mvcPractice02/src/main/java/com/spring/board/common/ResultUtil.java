package com.spring.board.common;

import lombok.Getter;
import lombok.Setter;

// 결과 값을 리턴하기 위한 클래스

@Getter
@Setter
public class ResultUtil {

	private String state = "FAIL";
	private String msg = "";
	private Object data = "";
	
}
