package com.board.controller.interceptor;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class LoggerInterceptorTest {
	/*
	 * Interceptor
	 * Controller에 들어오는 요청(HttpRequest), 응답(HttpResponse)을 가로채는 역할
	 * 단, 실행 시점이 DispatcherServlet 실행 후(Controller로 가기전) 실행됨
	 * 
	 * 참고 Filter는 Interceptor와 비슷한 기능이지만 실행 시점이 DispatcherServlet 실행 전에 실행됨
	*/
	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;
	
	@Autowired
	private RequestMappingHandlerMapping handlerMapping;
	
	@Test
	public void 인터셉터_테스트() throws Exception{
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/sample/getSample");
		request.setMethod("GET");
		
		MockHttpServletResponse responese = new MockHttpServletResponse();
		
		HandlerExecutionChain handlerExcutionChain = handlerMapping.getHandler(request);
		
		HandlerInterceptor[] interceptors = handlerExcutionChain.getInterceptors();
		
		for(HandlerInterceptor interceptor : interceptors) {
			interceptor.preHandle(request, responese, handlerExcutionChain.getHandler());
		}
		
		ModelAndView mav = handlerAdapter.handle(request, responese, handlerExcutionChain.getHandler());
		
		for(HandlerInterceptor interceptor : interceptors) {
			interceptor.postHandle(request, responese, handlerExcutionChain.getHandler(), mav);
		}
		
		assertEquals(200, responese.getStatus());
	}

}
