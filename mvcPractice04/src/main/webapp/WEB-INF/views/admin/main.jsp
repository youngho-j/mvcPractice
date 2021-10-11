<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/main.css">
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
                    <div>관리자 페이지 입니다.</div>
                </div>
                <div class="clearfix"></div>
			</div>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../includes/admin/footer.jsp" %>
	        
		</div>
	</div>
</body>
</html>