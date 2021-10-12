package com.spring.shop.vo;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthorVO {
	// 작가 아이디
    private int authorId;
    
    // 작가 이름 
    private String authorName;
    
    // 국가 id
    @Setter(AccessLevel.NONE)
    private String nationId;
    
    // 작가 소속국가
    @Setter(AccessLevel.NONE)
    private String nationName;
    
    // 작가 소개
    private String authorProfile;
    
    // 등록 일
    private Date regDate;
    
    // 수정 일
    private Date updateDate;
    
    public void setNationId(String nationId) {
    	this.nationId = nationId;
    	
    	  if(nationId.equals("01")) {
              this.nationName = "국내";
          } else {
              this.nationName = "국외";
          }
    }
}
