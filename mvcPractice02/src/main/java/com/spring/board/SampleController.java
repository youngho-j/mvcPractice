package com.spring.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sample")
public class SampleController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/getSample", method = RequestMethod.GET)
	public String getSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("= = = = = = = = = = = getSample = = = = = = = = = = =");
		return "test";
	}
}
