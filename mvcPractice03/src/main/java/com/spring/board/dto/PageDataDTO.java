package com.spring.board.dto;

import com.spring.board.model.PagingModel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDataDTO {
	
	// 보여지는 시작 페이지 번호
	private int startPage;
	
	// 보여지는 끝 페이지 번호
	private int endPage;
	
	// 다음 페이지 존재 여부
	private boolean prev;
	
	// 이전 페이지 존재 여부
	private boolean next;
	
	// 전체 게시글 수
	private int totalPost;
	
	// 페이징 관련 정보를 담은 객체
	private PagingModel pagingModel;
	
	public PageDataDTO(PagingModel pagingModel, int totalPost) {
		
		this.pagingModel = pagingModel;
		this.totalPost = totalPost;
		
		// 보여지는 마지막 페이지 번호
		this.endPage = (int)(Math.ceil(pagingModel.getCurPageNum() / 10.0)) * 10; 
		
		// 보여지는 시작 페이지 번호
		this.startPage = this.endPage - 9;
		
		// 전체 페이지 중 가장 마지막 페이지 번호 => 전체 게시글 수 / 페이지당 보여지는 게시글 수
		int lastPage = (int)(Math.ceil((totalPost * 1.0) / pagingModel.getViewPerPage()));
		
		// 보여지는 마지막 페이지보다 전체 페이지 중 가장 마지막 페이지가 작을 경우 보여지는 마지막 페이지를 전체 페이지 중 마지막 페이지로 변경
		if(lastPage < this.endPage) {
			this.endPage = lastPage;
		}
		
		this.prev = this.startPage > 1;
		
		this.next = this.endPage < lastPage;
	}
}
