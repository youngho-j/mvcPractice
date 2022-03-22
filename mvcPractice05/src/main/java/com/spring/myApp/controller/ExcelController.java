 package com.spring.myApp.controller;

import java.io.ByteArrayOutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.myApp.util.ExcelUtil;

@RestController
public class ExcelController {
	
	private ExcelUtil excelUtil;
	
	public ExcelController(ExcelUtil excelUtil) {
		this.excelUtil = excelUtil;
	}
	
	@PostMapping(value = "/downloadExcel")
	public ResponseEntity<byte[]> downloadExcel(@RequestBody Map<String, Object> params) throws Exception {
		if(!MapUtils.isEmpty(params)) {
			
			Workbook workbook = excelUtil.makeExcelFile(params);			
			
			if(workbook.getNumberOfSheets() > 0) {
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				
				workbook.write(os);
					
				workbook.close();
				
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
