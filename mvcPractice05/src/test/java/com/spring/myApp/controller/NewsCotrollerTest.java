package com.spring.myApp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class NewsCotrollerTest {
	
	@Autowired
	private NewsController newsController;
	
	private MockMvc mock;
	
	@Before
	public void setUp() {
		this.mock = MockMvcBuilders
				.standaloneSetup(newsController)
				.build();
	}
	
	@Test
	public void 기사_정보_JSON_리턴_테스트() throws Exception {
		mock.perform(get("/crawling"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print());
	}
}
