<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/authorManage.css">
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
                    <div class="admin_contents_title"><span>작가 관리</span></div>
                    <div class="admin_contents_body">
                    	<c:if test="${checkResult != 'empty'}">
	                    	<table class="author_table">
	                    		<thead>
	                    			<tr>
	                    				<td class="th_column_1">작가 번호</td>
	                    				<td class="th_column_1">작가 이름</td>
	                    				<td class="th_column_2">작가 국가</td>
	                    				<td class="th_column_3">등록 날짜</td>
	                    				<td class="th_column_3">수정 날짜</td>
	                    			</tr>
	                    		</thead>
	                    		<c:forEach items="${list}" var="list">
	                    		<tr>
	                    			<td>
	                    				<c:out value="${list.authorId}"/>
	                    			</td>
	                    			<td>
	                    				<a class="move" href='<c:out value="${list.authorId}"/>'>
	                    					<c:out value="${list.authorName}"/>
	                    				</a>
	                    			</td>
	                    			<td>
	                    				<c:out value="${list.nationName}"/>
	                    			</td>
	                    			<td>
	                    				<fmt:formatDate value="${list.regDate}" pattern="yyyy-MM-dd"/>
	                    			</td>
	                    			<td>
	                    				<fmt:formatDate value="${list.updateDate}" pattern="yyyy-MM-dd"/>
	                    			</td>
	                    		</tr>
	                    		</c:forEach>
	                    	</table>
                    	</c:if>
                    	<c:if test="${checkResult == 'empty'}">
                    		<div class="author_table_empty">
                				등록된 작가가 없습니다.
                			</div>
                    	</c:if>          
                    </div>
                    
                    <!-- 검색 인터페이스 영역 -->
                    <div class="search_area">
                    	<form id="searchForm" action="/admin/authorManage" method="get">
                    		<div class="search_input_area">
                    			<input type="text" name="keyword" value='<c:out value="${pagingManager.pageInfo.keyword}"></c:out>'>
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
                    <form id="moveForm" action="/admin/authorManage" method="get">
						<input type="hidden" name="pageNum" value="${pagingManager.pageInfo.pageNum}">
						<input type="hidden" name="viewPerPage" value="${pagingManager.pageInfo.viewPerPage}">
						<input type="hidden" name="keyword" value="${pagingManager.pageInfo.keyword}">
					</form>
                    
                </div>
                <div class="clearfix"></div>
			</div>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../includes/admin/footer.jsp" %>
	        
		</div>
	</div>
<script type="text/javascript">
$(document).ready(function(){
    
    let result = '<c:out value="${enroll_result}"/>';
    let modifyResult = '<c:out value="${modifyResult}"/>';
    let deleteResult = '<c:out value="${deleteResult}"/>';
    
    checkResult(result);
    
    checkModifyResult(modifyResult);
    
    checkDeleteResult(deleteResult);
    
});

function checkResult(result){
	if(result === ''){
    	return;
    }
    alert("작가 '${enroll_result}' 을 등록하였습니다.");
}

function checkModifyResult(modifyResult) {
	if(modifyResult === '1'){
		alert("작가 정보 수정을 완료하였습니다.");
	} else if(modifyResult === '0') {
		alert("작가 정부 수정을 하지 못하였습니다.")	
	}
}

function checkDeleteResult(deleteResult) {
	if(deleteResult == 1){
		alert("작가 정보 삭제 완료!");
	} else if(deleteResult == 2){
		alert("해당 작가의 이름으로 등록된 책이 존재하므로 정보를 삭제할 수 없습니다.");
	}	
}

let moveForm = $("#moveForm");

/* 페이지 이동 */
$(".pagingManager_btn a").on("click", function(e){
    
    e.preventDefault();
    
    moveForm.find("input[name='pageNum']").val($(this).attr("href"));
    
    moveForm.submit();
});

/* 검색 기능 */
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

/* 작가 상세 페이지로 이동 */
$(".move").on("click", function(e){
	
	e.preventDefault();
	
	moveForm.append("<input type='hidden' name='authorId' value='"+ $(this).attr("href") + "'>");
	moveForm.attr("action", "/admin/authorDetail");
	moveForm.submit();
});
</script>
</body>
</html>