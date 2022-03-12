package com.spring.myApp.enums;

public enum SearchTerms {
	Olympic("올림픽"),
	PresidentialElection("대통령선거"),
	Covid("코로나"),
	War("전쟁"),
	Cryptocurrency("암호화폐");
	
	private final String keyword;

	SearchTerms(String keyword) {
		this.keyword = keyword;
	}
	
	public String getKeyword() {return keyword;}
}
