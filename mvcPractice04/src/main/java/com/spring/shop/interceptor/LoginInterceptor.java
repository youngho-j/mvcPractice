package com.spring.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		log.info("LoginInterceptor preHandle 메서드 실행");
		
		HttpSession session  = request.getSession();
		
		session.invalidate();
		log.info("LoginInterceptor preHandle - 세션 제거");
		return true;
	}
	
}
