package com.spring.myApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NewsInfoDTO {
	private final String newsURL;
	private final String newsTitle;
}
