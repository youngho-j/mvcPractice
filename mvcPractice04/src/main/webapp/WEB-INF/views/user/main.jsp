<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wellcome MyShop</title>
<link rel="stylesheet" href="/resources/css/user/main.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<%@include file="../user/includes/topNavi.jsp" %>
		
		<div class="top_area">
		
			<%@include file="../user/includes/logo.jsp" %>
			
			<%@include file="../user/includes/searchBar.jsp" %>
			
			<%@include file="../user/includes/loginArea.jsp" %>
			
			<div class="clearfix"></div>
		</div>
		
		<%@include file="../user/includes/naviBar.jsp" %>
		
		<div class="content_area">
			<h1>content area</h1>
		</div>
        
		<%@include file="../user/includes//footer.jsp" %>
        
	</div>
</div>
<script type="text/javascript">
<!-- top_Navi 로그아웃 -->
$("#top_navi_logout_btn").click(function(){
	$.ajax({
		type:"POST",
		url:"/member/logout",
		success:function(data){
			document.location.reload();
		}
	});
});
</script>
</body>
</html>