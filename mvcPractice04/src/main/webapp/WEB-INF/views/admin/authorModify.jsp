<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/authorModify.css">
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
                    	<form action="/admin/authorModify" method="post">
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
                   				<input class="input_block" name="authorName" value="<c:out value='${authorInfo.authorName}'/>">
                   				<span id="authorName_msg"></span>
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
                   				<span id="authorProfile_msg"></span>
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
                   			<button id="cancelBtn" class="btn">상세 페이지</button>
	                    	<button id="modifyBtn" class="btn modify_btn">상품 수정</button>
	                    	<button id="deleteBtn" class="btn delete_btn">상품 삭제</button>
	                    </div>
	                    
	                    <!-- csrf 토큰 -->
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	                    </form>
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
let modifyForm = $("#modifyForm");

/* 작가 상세 페이지로 이동 */
$("#cancelBtn").on("click", function(e){
	
	e.preventDefault();
	
	moveForm.attr("action", "/admin/authorDetail")
	moveForm.submit();
});

/* 작가 수정 적용 */
$("#modifyBtn").on("click", function(e){
	
	let authorName = $(".form_section_content input[name='authorName']").val();
	let authorProfile = $(".form_section_content textarea").val();		

	let	nameCk = false;
	let profileCk = false;
	
	e.preventDefault();
	
	/* 작가 이름 유효성 검사 */
	if(!authorName) {
		$("#authorName_msg").html("작가 이름을 입력해주세요.");
		$("#authorName_msg").css("display", "block");
	} else {
		$("#authorName_msg").html("");
		$("#authorName_msg").css("display", "none");
		nameCk = true;
	}
	
	/*작가 소개 유효성 검사 */
	if(!authorProfile) {
		$("#authorProfile_msg").html("작가 소개를 입력해주세요.");
		$("#authorProfile_msg").css("display", "block");
	} else {
		$("#authorProfile_msg").html("");
		$("#authorProfile_msg").css("display", "none");
		authorProfile = true;
	}
	
	if(nameCk && introCk){
		modifyForm.submit();	
	} else {
		return false;
	}
});

/* 작가 정보 삭제  */
$("#deleteBtn").on("click", function(e) {
	e.preventDefault();
	
	moveForm.find("input").remove();
	
	moveForm.append('<input type="hidden" name="authorId" value="${authorInfo.authorId}">');
	moveForm.append('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>');
	
	moveForm.attr("action", "/admin/authorDelete");
	moveForm.attr("method", "post");
	moveForm.submit();
});

/* 입력시 메세지 지우기 */
$("input[name='authorName']").on("focus", function() {
	$("#authorName_msg").css("display","none");
});
$("textarea").on("focus", function() {
	$("#authorProfile_msg").css("display","none");
});
</script>
</body>
</html>