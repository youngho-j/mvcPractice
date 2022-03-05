package com.spring.myApp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.myApp.enums.ExcelFontSize;
import com.spring.myApp.enums.SelectOption;

@Component
public class ExcelUtil {
	
	public String makeExcelFileName(Map<String, Object> params) {
		
		String firstWord = LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yyMMdd_HH시"));
		
		String keyword = params.get("keyword").toString();
		
		String selectOption = SelectOption
				.OptionCheck(params.get("selectOption").toString())
				.getSearchOption();
		
		return firstWord + "_" + keyword + selectOption + "뉴스";
	}
	
	public Workbook makeExcelFile(Map<String, Object> params) {
		CellStyle style;
		
		int rowNum = 0;
		
		String keyword = params.get("keyword").toString();
		
		String selectOption = SelectOption
				.OptionCheck(params.get("selectOption").toString())
				.getSearchOption();
		
		ObjectMapper mapper = new ObjectMapper();
		
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, String>> list = mapper.convertValue(params.get("newsList"), List.class);
		
		Workbook workbook = new XSSFWorkbook();
		
		// 시트 생성
		Sheet sheet = workbook.createSheet("Sheet1");
		
		// 제목 영역
		// 제목 폰트
		Font titleFont = workbook.createFont();
		titleFont.setFontName(HSSFFont.FONT_ARIAL);
		titleFont.setFontHeightInPoints(ExcelFontSize.TWELVE.getValue());
		titleFont.setBold(true);
		titleFont.setColor(IndexedColors.AQUA.getIndex());
					
		// 셀 스타일 - 제목
		style = workbook.createCellStyle();
		style.setFont(titleFont);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		
		// 제목 셀 생성
		Cell titleCell = sheet.createRow(rowNum).createCell(0); // rowNum : 0
		titleCell.setCellStyle(style);
		titleCell.setCellValue(keyword + " " + selectOption + " 뉴스");
		
		// 셀 병합(제목 들어갈 셀)
		sheet.addMergedRegion(
					new CellRangeAddress(rowNum, rowNum, 0, 2));
		
		// 제목 영역 아래 빈행
		sheet.createRow(++rowNum); // rowNum : 1
		
		// 테이블 헤더
		// 테이블 헤더 폰트
		Font tableHeaderFont = workbook.createFont();
		tableHeaderFont.setFontName(HSSFFont.FONT_ARIAL);
		tableHeaderFont.setFontHeightInPoints(ExcelFontSize.TEN.getValue());
		tableHeaderFont.setBold(true);
		tableHeaderFont.setColor(IndexedColors.BLUE_GREY.getIndex());
					
		// 테이블 헤더 영역 셀 스타일 || 참고, 스타일 20 ~ 30회 이상 될 경우 스타일 적용되지 않는 버그 발생 - cellstyle 객체 재사용
		style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.MEDIUM);
		style.setBottomBorderColor(IndexedColors.ORANGE.getIndex());
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(tableHeaderFont);
		
		// 테이블 헤더 영역(순서)
		Cell tableHeaderNumber = sheet.createRow(++rowNum).createCell(0); // rowNum : 2
		tableHeaderNumber.setCellStyle(style);
		tableHeaderNumber.setCellValue("No");
		
		// 테이블 헤더 영역(제목)
		Cell tableHeaderTitle;
			
		for(int i = 1 ; i < 7 ; i++) {
			tableHeaderTitle = sheet.getRow(rowNum).createCell(i); // rowNum : 2
			tableHeaderTitle.setCellStyle(style);
			
			if(i == 1) {
				tableHeaderTitle.setCellValue("제목");							
			}
		}
		
		// 테이블 뉴스 제목 입력 칸 셀 병합
		sheet.addMergedRegion(
				new CellRangeAddress(rowNum, rowNum, 1, 6)); // rowNum : 2
		
		// 테이블 바디
		// 테이블 바디 폰트
		Font tableBodyFont = workbook.createFont();
		tableBodyFont.setFontName(HSSFFont.FONT_ARIAL);
		tableBodyFont.setFontHeightInPoints(ExcelFontSize.EIGHT.getValue());
		tableBodyFont.setBold(true);
		
		// 테이블 바디 순번 스타일
		style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(tableBodyFont);
		
		// 테이블 바디 순번 셀 생성
		Cell tableBodyNumber;
		for(int i = 1 ; i <= list.size() ; i++) {
			tableBodyNumber = sheet.createRow(i + rowNum).createCell(0); // rowNum : 2
			tableBodyNumber.setCellStyle(style);
			tableBodyNumber.setCellValue(i);			
		}
		
		// 하이퍼링크 폰트
		Font hyperlinkFont = workbook.createFont();
		hyperlinkFont.setFontName(HSSFFont.FONT_ARIAL);
		hyperlinkFont.setFontHeightInPoints(ExcelFontSize.EIGHT.getValue());
		hyperlinkFont.setUnderline(Font.U_SINGLE);
		hyperlinkFont.setColor(IndexedColors.BLUE.getIndex());
		hyperlinkFont.setBold(true);
		
		// 테이블 바디 하이퍼링크 스타일
		style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(hyperlinkFont);
		
		// 테이블 바디 제목(링크)
		Cell tableBodyLink;
		// 테이블 바디 제목(링크) 첫줄 위치
		++rowNum;
		
		for(int i = 0 ; i < list.size() ; i++) {
			
			for(int j = 1 ; j < 7 ; j++) {
				tableBodyLink = sheet.getRow(i + rowNum).createCell(j); // rowNum : 3
				
				if(j == 1) {
					tableBodyLink.setCellValue(list.get(i).get("newsTitle"));
					Hyperlink link = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
					link.setAddress(list.get(i).get("newsURL"));
					tableBodyLink.setHyperlink(link);
				}
				
				tableBodyLink.setCellStyle(style);
				
			}
			// 테이블 뉴스 제목 입력 칸 셀 병합
			sheet.addMergedRegion(
					new CellRangeAddress(i + rowNum, i + rowNum, 1, 6)); // rowNum : 3
			
		}
		
		return workbook;
	}
}
