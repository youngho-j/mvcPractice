package com.spring.board.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

// 컨트롤러를 호출해서 파일 다운로드
public class FileDownloadUtil extends AbstractView {
	private static final Logger logger = LoggerFactory.getLogger(FileDownloadUtil.class);
	
	public FileDownloadUtil() {
		setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		OutputStream out = response.getOutputStream();
		 
        FileInputStream fis = null;
		
		/*
		 * fileInfo = 전송 받은 모델에 담긴 파일 정보를 담은 map 객체 
		 * fileNamekey = 실제 저장된 파일 명
		 * fileName = 원본 파일 명
		 * filePath = 파일이 저장된 경로
		 */
		
		@SuppressWarnings("unchecked")
		Map<String, Object> fileInfo = (Map<String, Object>) model.get("fileInfo");
		
		String fileNameKey = (String) fileInfo.get("fileNameKey"); 
	    String fileName = (String) fileInfo.get("fileName");
	    String filePath = (String) fileInfo.get("filePath");
	    
	    // parent = 경로, child = 파일명
	    File file = new File(filePath, fileNameKey);
	    
	    response.setContentType(getContentType());
	    response.setContentLength(LongToInt(file.length()));
	    
	    // 브라우저, OS 정보
	    String userAgent = request.getHeader("User-Agent");
	    
	    // OS(윈도우), IE 인경우 
        if (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("Trident") > -1) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String( fileName.getBytes("UTF-8"), "8859_1");
        }
        
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        
        try {
        	fis = new FileInputStream(file);
        	FileCopyUtils.copy(fis, out);
        	logger.info("성공");
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
			if(fis != null) {
				fis.close();
			}
		}
        out.flush();
	}
	
	protected int LongToInt(long value) {
		return Math.toIntExact(value);
	}
}
