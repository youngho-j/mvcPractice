<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작가 목록</title>
<link rel="stylesheet" href="../resources/css/admin/authorSearch.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
	<div class="wrapper">
		<!-- 제목 영역 -->
		<div class="content_title_area">
				<span>작가 선택</span>
		</div>
		<!-- 게시물 영역 -->
		<div class="content_body_area">
			<div class="author_table_area">
				<!-- 게시물 테이블 영역 -->
				<c:choose>
					<c:when test="${listData eq 'empty'}">
						<div class="empty_table_area">
			            	등록된 작가가 없습니다.
			            </div>
					</c:when>
					<c:otherwise>
						<div class="exist_table_area">
							<table class="author_table">
								<thead>
				                	<tr>
					            		<td class="th_column_1">작가 번호</td>
					                    <td class="th_column_2">작가 이름</td>
					                    <td class="th_column_3">작가 국적</td>
			                  		</tr>
			                  	</thead>
			                  	<c:forEach items="${listData}" var="list">
			                  		<tr>
			                  			<td><c:out value="${list.authorId}"/></td>
				                    	<td>
				                    		<a class="move" href='<c:out value="${list.authorId}"/>' data-name='<c:out value="${list.authorName}"/>'>
				                    			<c:out value="${list.authorName}"/>
				                    		</a>
				                    	</td>
				                    	<td><c:out value="${list.nationName}"/></td>
			                  		</tr>
			                  	</c:forEach>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			
			<!-- 검색 인터페이스 영역 -->
	        <div class="search_area">
				<form id="searchForm" action="/admin/authorSearch" method="get">
	            	<div class="search_input_area">
	                	<input type="text"   name="keyword" value='<c:out value="${pagingManager.pageInfo.keyword}"></c:out>'>
	                    <input type="hidden" name="pageNum" value='<c:out value="${pagingManager.pageInfo.pageNum}"></c:out>'>
	                    <input type="hidden" name="viewPerPage" value='${pagingManager.pageInfo.viewPerPage}'>
	                    <button class="btn search_btn">검 색</button>
	                </div>
				</form>
			</div>  
			
			<!-- 페이지 이동 인터페이스 영역 -->
	        <div class="pagingManger_area">
	        	<ul class="pagingManger_body">
	                    		
	            	<!-- 이전 버튼 -->
	                <c:if test="${pagingManager.prev}">
	                	<li class="pagingManager_btn prev">
	                    	<a href="${pagingManager.pageStartNum - 1}">이전</a>
	                    </li>
	                </c:if>
	                    		
	                <!-- 페이지 번호 -->
	                <c:forEach begin="${pagingManager.pageStartNum}" end="${pagingManager.pageEndNum}" var="num">
	                	<li class="pagingManager_btn ${pagingManager.pageInfo.pageNum == num ? 'active':''}">
	                    	<a href="${num}">${num}</a>
	                    </li>
	                </c:forEach>
	                    		
	                <!-- 다음 버튼 -->
	                <c:if test="${pagingManager.next}">
	                	<li class="pagingManger_btn next">
	                    	<a href="${pagingManager.pageEndNum + 1}">다음</a>
	                    </li>
	                </c:if>
	                
	            </ul>
			</div>
			
			<!-- 페이지 이동 form -->
	        <form id="moveForm" action="/admin/authorSearch" method="get">
				<input type="hidden" name="pageNum" value="${pagingManager.pageInfo.pageNum}">
				<input type="hidden" name="viewPerPage" value="${pagingManager.pageInfo.viewPerPage}">
				<input type="hidden" name="keyword" value="${pagingManager.pageInfo.keyword}">
			</form>
		</div>
		
	</div>
<script type="text/javascript">
/* 검색 */
let searchForm = $("#searchForm");

$("#searchForm button").on("click", function(e){
	
	e.preventDefault();
	
	/* 검색 키워드 유효성 검사 */
	if(!searchForm.find("input[name='keyword']").val()){
		alert("키워드를 입력하십시오");
		return false;
	}
	/* 검색시 해당 페이지 상태에서 검색이 되기 때문에 페이지 번호 1로 초기화 */
	searchForm.find("input[name='pageNum']").val("1");
	
	searchForm.submit();
});

/* 페이지 이동 */
let moveForm = $("#moveForm");

$(".pagingManager_btn a").on("click", function(e){
    
    e.preventDefault();
    
    moveForm.find("input[name='pageNum']").val($(this).attr("href"));
    
    moveForm.submit();
});

/* 작가 선택 후 데이터 전달 및 팝업창 종료하기 */
$(".move").on("click", function(e){
	e.preventDefault();
	
	let authorId = $(this).attr("href");
	/* data-name 속성에 저장된 값을 가져옴 */
	let authorName= $(this).data("name");
	
	/* opener 사용하여 부모 창의 요소에 접근 */
	$(opener.document).find("#input_authorId").val(authorId);
	$(opener.document).find("#input_authorName").val(authorName);
			
	window.close();

});		
</script>
</body>
</html>