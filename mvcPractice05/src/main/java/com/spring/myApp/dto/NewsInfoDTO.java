package com.spring.myApp.dto;

import java.beans.ConstructorProperties;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

@Getter
public class NewsInfoDTO {
	private String newsURL;
	private String newsTitle;
	
	@JsonCreator
	@ConstructorProperties({"newsURL", "newsTitle"})
	public NewsInfoDTO(String newsURL, String newsTitle) {
		this.newsURL = newsURL;
		this.newsTitle = newsTitle;
	}
}
