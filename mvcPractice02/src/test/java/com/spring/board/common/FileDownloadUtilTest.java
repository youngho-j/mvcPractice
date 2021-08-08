package com.spring.board.common;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class FileDownloadUtilTest {
	
	private FileDownloadUtil util;
	
	@Before
	public void setUp() throws Exception {
		util = new FileDownloadUtil();
	}
	
	@Test
	public void 파일다운로드_유틸_컨텐츠타입_테스트() throws Exception {
		assertThat(util.getContentType()).isEqualTo("application/download; charset=utf-8");
	}
	
	@Test
	public void 파일다운로드_유틸_타입변환_테스트() throws Exception {
		int test = util.LongToInt(153L);
		assertThat(test).isEqualTo(153);
	}
	
	@Test
	public void 파일다운로드_테스트() throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("fileNameKey", "17b26dd6407a41e69b6fcacb500733b0.png");
		fileInfo.put("fileName", "링크이미지.png");
		fileInfo.put("filePath", "H:\\RebuildProject2file");
		
		model.put("fileInfo", fileInfo);
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36");
		
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		util.renderMergedOutputModel(model, request, response);
		
		assertNotNull(response);
		assertThat(response.getHeader("Content-Transfer-Encoding")).isEqualTo("binary");
	}
}
