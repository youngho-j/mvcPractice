<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyShop 로그인</title>
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<link rel="stylesheet" href="/resources/css/member/login.css">
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<form id="login_form" method="post">
			<div class="logo_area">
				<span>MyShop Mall</span>
			</div>
			
			<div class="login_area"> 
				
				<div class="id_area">
					<div class="id_input_box">
						<input class="id_input" name="memberId">
					</div>
				</div>
				
				<div class="pw_area">
					<div class="pw_input_box">
						<input class="pw_iput" name="memberPw">
					</div>
				</div>
				
				<c:if test="${result == 0}">
					<div class="login_msg">ID 또는 비밀번호를 잘못 입력하셨습니다.</div>
				</c:if>
				
				<div class="login_btn_area">
					<input type="button" class="login_btn" value="로그인">
				</div>			
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	/* 로그인 버튼 */
	$(".login_btn").click(function(){
		
		$("#login_form").attr("action", "/member/login");
        $("#login_form").submit();
	
	});
</script>
</body>
</html>