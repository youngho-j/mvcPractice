<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wellcome MyShop</title>
<link rel="stylesheet" href="/resources/css/main.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<div class="top_navi_area">
			<ul class="list">
				<c:if test="${member == null}">
					<li>
						<a href="/member/goLogin">로그인</a>
					</li>
					<li>
						<a href="/member/join">회원가입</a>
					</li>
				</c:if>
				<c:if test="${member != null}">
					<c:if test="${member.adminCk == 1 }">
						<li><a href="/admin/main">관리자 페이지</a></li>
					</c:if>
					<li>
						<a id="top_navi_logout_btn">로그아웃</a>
					</li>
					<li>
						<a href="">마이룸</a>
					</li>
					<li>
						<a href="">장바구니</a>
					</li>
				</c:if>
					<li>
						<a href="">고객센터</a>
					</li>
			</ul>
		</div>
		
		<div class="top_area">
			
			<div class="logo_area">
				<h1>logo area</h1>
			</div>
			
			<div class="search_area">
				<h1>search area</h1>
			</div>
			
			<div class="login_area">
				
				<c:if test="${member == null}">
					<div class="login_btn"><a href="/member/goLogin">로그인</a></div>
					<span><a href="/member/join">회원가입</a></span>
				</c:if>
				
				<c:if test="${member != null }">
					<div class="login_info">
						<span>회원 : ${member.memberName }</span>
						<span>충전금액 : <fmt:formatNumber value="${member.money }" pattern="#,###.## 원"/></span>
						<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,### 원"/></span>
						<span><a href="/member/logout">로그아웃</a></span>
					</div>
				</c:if>
				
			</div>
			
			<div class="clearfix"></div>
		</div>
		
		<div class="navi_area">
			<h1>navi area</h1>
		</div>
		
		<div class="content_area">
			<h1>content area</h1>
		</div>
	
	</div>
</div>
<script type="text/javascript">
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