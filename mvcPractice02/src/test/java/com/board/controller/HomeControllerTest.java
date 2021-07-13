package com.board.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
//Controller및 web환경에 사용되는 빈들을 자동으로 생성하여 등록
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class HomeControllerTest {
	private MockMvc mock;
	
	@Autowired
	HomeController homeController;
	
	@Before
	public void setup() {
		this.mock = MockMvcBuilders.standaloneSetup(homeController).build();
	}
	
	@Test
	public void 출력확인_테스트() throws Exception {
		mock.perform(get("/board"))
		.andExpect(status().isOk())
		.andExpect(content().string("Index"));
	}

	@Test
	public void 출력확인_테스트2() throws Exception {
		mock.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(content().string("home"));
	}
}
