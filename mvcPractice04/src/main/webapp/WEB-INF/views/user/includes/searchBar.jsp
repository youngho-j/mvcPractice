<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 검색창 영역 -->
<div class="search_area">
	<div class="searchForm_area">
    	<form id="searchForm" action="/search" method="get">
        	<div class="search_input">
            	<select name="type">
                	<option value="T">책 제목</option>
                	<option value="A">작가명</option>
                	<option value="C">카테고리 코드</option>
            	</select>
            	<input type="text" name="keyword" value='<c:out value="${pagingManager.pageInfo.keyword}"/>'>
            	<button class='btn search_btn'>검 색</button>                				
        	</div>
    	</form>
	</div>
</div>