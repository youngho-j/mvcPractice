package com.spring.board.interceptor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring.board.SampleController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class LoggerInterceptorTest {
	/*
	 * Interceptor
	 * Controller에 들어오는 요청(HttpRequest), 응답(HttpResponse)을 가로채는 역할
	 * 단, 실행 시점이 DispatcherServlet 실행 후(Controller로 가기전) 실행됨
	 * 
	 * 참고 Filter는 Interceptor와 비슷한 기능이지만 실행 시점이 DispatcherServlet 실행 전에 실행됨
	*/

	@Autowired
	private SampleController sampleControler;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(sampleControler).build();
	}
	
	@Test
	public void 인터셉터_테스트() throws Exception {
		mockMvc.perform(get("/sample/getSample"))
		.andExpect(status().isOk())
		.andExpect(content().string("test"))
		.andDo(log());
	}
}
