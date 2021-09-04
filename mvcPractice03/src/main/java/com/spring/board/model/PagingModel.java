package com.spring.board.model;

import lombok.ToString;

@ToString
public class PagingModel {
	
	// 현재 페이지
	private int curPageNum;
	
	// 페이지 당 보여지는 게시물 수
	private int viewPerPage;
	
	// 스킵 할 게시물 수 (curPageNum - 1) * viewPerPage
	private int skipOverPost;
	
	public PagingModel() {
		this(1, 10);
	}
	
	public PagingModel(int curPageNum, int viewPerPage) {
		this.curPageNum = curPageNum;
		this.viewPerPage = viewPerPage;
		this.skipOverPost = (curPageNum - 1) * viewPerPage;
	}

	public int getCurPageNum() {
		return curPageNum;
	}

	public void setCurPageNum(int curPageNum) {
		this.curPageNum = curPageNum;
		// 페이지가 변경되면 보여지는 게시물들도 변경되어야 하므로 
		this.skipOverPost = (curPageNum - 1) * this.viewPerPage;
	}

	public int getViewPerPage() {
		return viewPerPage;
	}

	public void setViewPerPage(int viewPerPage) {
		this.viewPerPage = viewPerPage;
		this.skipOverPost = (curPageNum - 1) * this.viewPerPage;
	}

	public int getSkipOverPost() {
		return skipOverPost;
	}

	public void setSkipOverPost(int skipOverPost) {
		this.skipOverPost = skipOverPost;
	}
	
	
}
