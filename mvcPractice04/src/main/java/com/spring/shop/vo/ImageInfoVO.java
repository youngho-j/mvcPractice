package com.spring.shop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
	
	private ImageInfoVO(Builder builder) {
		this.bookId = builder.bookId;
		this.uploadPath = builder.uploadPath;
		this.uuid = builder.uuid;
		this.fileName = builder.fileName;
	}
	
	public static class Builder {
		// 업로드 경로 
		private String uploadPath;
		
		// 파일 uuid 
		private String uuid;
		
		// 파일 이름 
		private String fileName;
		
		// 상품 id
		private int bookId;
		
		public Builder builder() {
			return this;
		}
		
		public Builder bookId(int bookId) {
			this.bookId = bookId;
			return this;
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
			return new ImageInfoVO(this);
		}
		
	}
	
}
