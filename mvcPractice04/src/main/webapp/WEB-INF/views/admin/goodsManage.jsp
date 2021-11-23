<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/goodsManage.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
	<div class="wrapper">
		<div class="wrap">
			
			<!-- 최상단 네비, 상단 제목 영역 -->
			<%@include file="../includes/admin/header.jsp" %>
			
			<!-- 콘텐츠 네비 영역 -->
			<div class="admin_contents_navi_area">
			
				<!-- contents 영역 네비 -->
				<%@include file="../includes/admin/contentsNavi.jsp" %>
				
				<!-- contents 영역 내용 -->
				<div class="admin_contents_area">
                    <div class="admin_contents_title"><span>상품 관리</span></div>
                    <div class="admin_contents_body">
                    	<div class="goods_table_area">
	                    	<c:choose>
	                    		<c:when test="${checkResult ne 'empty'}">
	                    			<table class="goods_table">
	                    				<thead>
	                    					<tr>
		                    					<td class="th_column_2">상품번호</td>
			                    				<td class="th_column_3">상품이름</td>
			                    				<td class="th_column_3">작가이름</td>
			                    				<td class="th_column_1">카테고리</td>
			                    				<td class="th_column_2">재   고</td>
			                    				<td class="th_column_3">등록일자</td>
	                    					</tr>
	                    				</thead>
	                    				<c:forEach items="${list}" var="list">
	                    					<tr>
	                    						<td><c:out value="${list.bookId}"/></td>
				                    			<td>
				                    				<a class="move" href='<c:out value="${list.bookId}"/>'>
				                    					<c:out value="${list.bookName}"/>
				                    				</a>
				                    			</td>
				                    			<td><c:out value="${list.authorName}"/></td>
				                    			<td><c:out value="${list.categoryName}"/></td>
				                    			<td><c:out value="${list.bookStock}"/></td>
				                    			<td><fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/></td>
	                    					</tr>
	                    				</c:forEach>
	                    			</table>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<div class="empty_table">
	                					등록된 작가가 없습니다.
	                				</div>
	                    		</c:otherwise>
	                    	</c:choose>
                    	</div>
                    	
                    	<!-- 검색 영역 -->
	                	<div class="search_area">
	                		<form id="searchForm" action="/admin/goodsManage" method="get">
	                			<div class="search_input">
	                    			<input type="text" name="keyword" value='<c:out value="${pagingManager.pageInfo.keyword}"></c:out>'>
	                    			<input type="hidden" name="pageNum" value='<c:out value="${pagingManager.pageInfo.pageNum }"></c:out>'>
	                    			<input type="hidden" name="viewPerPage" value='${pagingManager.pageInfo.viewPerPage}'>
	                    			<input type="hidden" name="type" value="G">
	                    			<button class='btn search_btn'>검 색</button>                				
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
	                    <form id="moveForm" action="/admin/goodsManage" method="get">
							<input type="hidden" name="pageNum" value="${pagingManager.pageInfo.pageNum}">
							<input type="hidden" name="viewPerPage" value="${pagingManager.pageInfo.viewPerPage}">
							<input type="hidden" name="keyword" value="${pagingManager.pageInfo.keyword}">
						</form>
	                	
                    </div>
                </div>
                <div class="clearfix"></div>
			</div>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../includes/admin/footer.jsp" %>
	        
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		/* 등록 성공시 */
		let enrollResult = '<c:out value="${enrollResult}"/>';
		
		checkResult(enrollResult);
		
		/* 수정 성공시 */
		let modifyResult = '<c:out value="${modifyResult}"/>';
		
		if(modifyResult > 0) {
			alert("상품 정보 수정 성공!");
		}
		
		/* 삭제 성공시 */
		let deleteResult = '<c:out value="${deleteResult}"/>';
		
		if(deleteResult == 1) {
			alert("상품 정보 삭제 성공!");
		}
	});
	
	function checkResult(result) {
		if(result === '') {
			return;
		}
		
		alert("상품 '" + result + "' 이 등록되었습니다.");
	}
	
	let searchForm = $("#searchForm");
	let moveForm = $("#moveForm");
	
	/* 상품 검색 */
	$("#searchForm button").on("click", function(e){
	
		e.preventDefault();
		
		/* 검색 키워드 유효성 검사 */
		if(!searchForm.find("input[name='keyword']").val()){
			alert("키워드를 입력하세요.");
			return false;
		}
		
		searchForm.find("input[name='pageNum']").val("1");
		
		searchForm.submit();
	
	});
	
	/* 페이지 이동 */
	$(".pagingManager_btn a").on("click", function(e){
	
		e.preventDefault();
		
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		moveForm.submit();
	
	});
	
	/* 상품 상세정보 페이지 이동 */
	$(".move").on("click", function(e){
		
		e.preventDefault();
		
		moveForm.append("<input type='hidden' name='bookId' value='" + $(this).attr("href") + "'>");
		
		moveForm.attr("action", "/admin/goodsDetail");
		
		moveForm.submit();
	});
</script>
</body>
</html>