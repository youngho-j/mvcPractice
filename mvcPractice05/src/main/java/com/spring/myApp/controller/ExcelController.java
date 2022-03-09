 package com.spring.myApp.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.myApp.util.ExcelUtil;

@Controller
public class ExcelController {
	
	private ExcelUtil excelUtil;
	
	public ExcelController(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}
	
	@PostMapping(value = "/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel(@RequestBody Map<String, Object> params) throws Exception {
		if(!MapUtils.isEmpty(params)) {
			
			// 리턴 ResponseEntity
			// 참고 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=mankeys&logNo=221493130600
			Workbook workbook = excelUtil.makeExcelFile(params);			
			
			if(workbook.getNumberOfSheets() > 0) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				
				workbook.write(os);
					
				workbook.close();
					
				// 엑셀 Content-Type
				// 참고 : https://jhhan009.tistory.com/68
				// 엑셀 파일 이름 ?? 출력
				// 참고 : https://dololak.tistory.com/19 - 인코딩 처리후 디코딩 필요
				return  ResponseEntity.ok()
						.contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+  URLEncoder.encode(excelUtil.makeExcelFileName(params),"UTF-8") + ".xlsx")
						.body(os.toByteArray());
			}
		}
		// 204 리턴
		return ResponseEntity.noContent().build();
	}
}
