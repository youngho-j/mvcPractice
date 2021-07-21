package com.spring.board.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {
	
	protected final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);
	
//	@SuppressWarnings("rawtypes")
//	Controller로 가기 전에 실행되는 메서드 - Ex) 로그인 체크, 세션 존재 여부 체크시 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("= = = = = = = = = = = LoggerInterceptor START = = = = = = = = = = =");
		logger.debug("URI : [{}]", request.getRequestURI());
		
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			String value = request.getParameter(key);
			logger.debug("RequestParameter Data : [ " + key + " : " + value + " ]");
		}
		
		return super.preHandle(request, response, handler);
	}
//	Controller에서 View로 가기 전에 실행되는 메서드 - Ex) 메뉴 권한 체크
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("= = = = = = = = = = = LoggerInterceptor END = = = = = = = = = = =");
	}
	
}
