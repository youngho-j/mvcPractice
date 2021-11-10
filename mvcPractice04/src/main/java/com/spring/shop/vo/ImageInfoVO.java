package com.spring.shop.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImageInfoVO {
	
	// 업로드 경로 
	private String uploadPath;
	
	// 파일 uuid 
	private String uuid;
	
	// 파일 이름 
	private String fileName;
	
	// 상품 id 
	private int bookId;
	
	// 기본 생성자
	public ImageInfoVO() {}
	
	public static class Builder {
		// 업로드 경로 
		private String uploadPath;
		
		// 파일 uuid 
		private String uuid;
		
		// 파일 이름 
		private String fileName;
		
		// 상품 id (필수)
		private int bookId;
		
		public Builder(int bookId) {
			this.bookId = bookId;
		}
		
		public Builder uploadPath(String uploadPath) {
			this.uploadPath = uploadPath;
			return this;
		}
		
		public Builder uuid(String uuid) {
			this.uuid = uuid;
			return this;
		}
		
		public Builder fileName(String fileName) {
			this.fileName = fileName;
			return this;
		}
		
		public ImageInfoVO build() {
			ImageInfoVO imageInfo = new ImageInfoVO();
			
			imageInfo.bookId = bookId;
			imageInfo.uploadPath = uploadPath;
			imageInfo.uuid = uuid;
			imageInfo.fileName = fileName;
			
			return imageInfo;
		}
		
	}
	
}
