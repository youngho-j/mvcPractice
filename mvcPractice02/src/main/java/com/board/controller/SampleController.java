package com.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/sample")
public class SampleController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/getSample", method = RequestMethod.GET)
	public void getSample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("= = = = = = = = = = = getSample = = = = = = = = = = =");
	}
}
