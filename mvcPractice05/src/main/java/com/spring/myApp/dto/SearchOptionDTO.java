package com.spring.myApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SearchOptionDTO {
	private final String selectOption;
	private final String keyword;
}
