package com.spring.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		
		MemberVO loginInfo = (MemberVO) session.getAttribute("member");
		
		if(loginInfo == null || loginInfo.getAdminCk() == 0) {
			log.info("회원정보가 없거나 관리자 계정이 아닙니다.");
			response.sendRedirect("/main");
			return false;
		}
		
		log.info("관리자 계정 확인");
		return true;
	}
	
}
