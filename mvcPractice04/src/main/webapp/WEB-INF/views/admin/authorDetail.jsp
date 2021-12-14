<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/authorDetail.css">
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
			<%@include file="../admin/includes/header.jsp" %>
			
			<!-- 콘텐츠 네비 영역 -->
			<div class="admin_contents_navi_area">
			
				<!-- contents 영역 네비 -->
				<%@include file="../admin/includes/contentsNavi.jsp" %>
				
				<!-- contents 영역 내용 -->
				<div class="admin_contents_area">
                    <div class="admin_contents_title"><span>작가 상세</span></div>
                    <div class="admin_contents_body">
                    	<!-- 작가 번호 영역 -->
                    	<div class="form_section">
                   			<div class="form_section_title">
                   				<label>작가 번호</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" name="authorId" readonly="readonly" value="<c:out value='${authorInfo.authorId}'/>">
                   			</div>
                   		</div>
                   		
                   		<!-- 작가 이름 영역 -->                    
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>작가 이름</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" name="authorName" readonly="readonly" value="<c:out value='${authorInfo.authorName}'/>">
                   			</div>
                   		</div>
                   		
                   		<!-- 소속 국가 영역 -->
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>소속 국가</label>
                   			</div>
                   			<div class="form_section_content">
                   				<select class="input_block" name="nationId" >
                   					<option value="none" selected disabled="disabled">=== 선택 ===</option>
                   					<option value="01" disabled="disabled" <c:out value=" ${authorInfo.nationId eq '01' ?'selected':''}"/>>국내</option>
                   					<option value="02" disabled="disabled" <c:out value=" ${authorInfo.nationId eq '02' ?'selected':''}"/>>국외</option>
                   				</select>
                   			</div>
                   		</div>
                   		
                   		<!-- 작가 소개 영역 -->
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>작가소개</label>
                   			</div>
                   			<div class="form_section_content">
                   				<textarea class="input_block" name="authorProfile" readonly="readonly"><c:out value='${authorInfo.authorProfile}'/></textarea>
                   			</div>
                   		</div>
                   		
                   		<!-- 등록 날짜 영역 -->
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>등록 날짜</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.regDate}" pattern="yyyy-MM-dd"/>">
                   			</div>
                   		</div>
                   		
                   		<!-- 수정 날짜 영역 -->
                   		<div class="form_section">
                   			<div class="form_section_title">
                   				<label>수정 날짜</label>
                   			</div>
                   			<div class="form_section_content">
                   				<input class="input_block" type="text" readonly="readonly" value="<fmt:formatDate value="${authorInfo.updateDate}" pattern="yyyy-MM-dd"/>">
                   			</div>
                   		</div>
                   		
                   		<!-- 버튼 영역 -->
                   		<div class="btn_section">
                   			<button id="cancelBtn" class="btn">작가 목록</button>
	                    	<button id="modifyBtn" class="btn modify_btn">수정 페이지</button>
	                    </div> 
                	</div>
                </div>
                <div class="clearfix"></div>
			</div>
            
            <!-- 페이지 이동 form -->
			<form id="moveForm" method="get">
				<input type="hidden" name="authorId" value='<c:out value="${authorInfo.authorId}"/>'>
				<input type="hidden" name="pageNum" value='<c:out value="${PreviousPageInfo.pageNum}"/>'>
				<input type="hidden" name="viewPerPage" value='<c:out value="${PreviousPageInfo.viewPerPage}"/>'>
				<input type="hidden" name="keyword" value='<c:out value="${PreviousPageInfo.keyword}"/>'>
			</form>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../admin/includes/footer.jsp" %>
	        
		</div>
	</div>
<script type="text/javascript">
let moveForm = $("#moveForm");

/* 작가 관리 페이지로 이동 */
$("#cancelBtn").on("click", function(e){
	
	e.preventDefault();
	
	/* 작가 목록 페이지에선 사용하지 않음 */
	$("input[name=authorId]").remove();
	
	moveForm.attr("action", "/admin/authorManage")
	moveForm.submit();
});

/* 작가 수정 페이지 이동 버튼 */
$("#modifyBtn").on("click", function(e){
	
	e.preventDefault();
	
	moveForm.attr("action", "/admin/authorModify");
	moveForm.submit();
});
</script>
</body>
</html>