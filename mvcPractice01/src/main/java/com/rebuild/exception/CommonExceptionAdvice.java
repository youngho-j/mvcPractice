package com.rebuild.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	// 지정한 예외 클래스 타입을 처리(해당 클래스의 목적)
	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, Model model) {
		log.error("Exception : " + ex.getMessage());
		
		//JSP 화면에서 구체적인 메세지를 보기 위해 전달
		model.addAttribute("exception", ex);
		log.error(model);
		return "error_page";
	}
	
	// 404 에러 처리
	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handle404(NoHandlerFoundException nhe) {
		return "custom404";
	}
}
