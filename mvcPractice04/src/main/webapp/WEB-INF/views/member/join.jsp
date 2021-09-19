<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyShop 회원가입</title>
<link rel="stylesheet" href="/resources/css/member/join.css">
<script src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
<div class="wrapper">
	<form id="join_form" method="post">
	<div class="wrap">
			
			<div class="title">
				<span>회원가입</span>
			</div>
			
			<div class="id_area">
				<div class="id_title">아이디</div>
				<div class="id_input_box">
					<input class="id_input" name="memberId">
				</div>
				<span class="id_input_msg1">사용 가능한 아이디입니다.</span>
				<span class="id_input_msg2">아이디가 이미 존재합니다.</span>
			</div>
			
			<div class="pw_area">
				<div class="pw_title">비밀번호</div>
				<div class="pw_input_box">
					<input class="pw_input" name="memberPw">
				</div>
			</div>
			
			<div class="pw_check_area">
				<div class="pw_check_title">비밀번호 확인</div>
				<div class="pw_check_input_box">
					<input class="pw_check_input">
				</div>
			</div>
			
			<div class="user_area">
				<div class="user_title">이름</div>
				<div class="user_input_box">
					<input class="user_input" name="memberName">
				</div>
			</div>
			<div class="mail_area">
				<div class="mail_title">이메일</div> 
				<div class="mail_input_box">
					<input class="mail_input" name="memberMail">
				</div>
				
				<div class="mail_check_area">
					<div class="mail_check_input_box">
						<input class="mail_check_input">
					</div>
					<div class="mail_check_btn">
						<span>인증번호 전송</span>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
			
			<div class="address_area">
				<div class="address_title">주소</div>
				
				<div class="address_input_area1">
					<div class="address_input_box1">
						<input class="address_input1" name="memberAddr1">
					</div>
					<div class="address_btn">
						<span>주소 찾기</span>
					</div>
					<div class="clearfix"></div>
				</div>
				
				<div class ="address_input_area2">
					<div class="address_input_box2">
						<input class="address_input2" name="memberAddr2">
					</div>
				</div>
				
				<div class ="address_input_area3">
					<div class="address_input_box3">
						<input class="address_input3" name="memberAddr3">
					</div>
				</div>
			</div>
			
			<div class="join_btn_area">
				<input type="button" class="join_btn" value="가입하기">
			</div>
			
		</div>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		
		/* 회원가입 */
		$(".join_btn").click(function(){
			$("#join_form").attr("action", "/member/join");
			$("#join_form").submit();
		});
		
		/* 아이디 중복 검사 */
		$(".id_input").on("propertychange change keyup paste input", function(){

			let memberId = $(".id_input").val();
			let data = {memberId : memberId}
			
			$.ajax({
				type : "POST",
				url : "/member/memberIdChk",
				data : data,
				success : function(result) {
					if(result != 'fail'){
						$('.id_input_msg1').css("display","inline-block");
						$('.id_input_msg2').css("display", "none");				
					} else {
						$('.id_input_msg2').css("display","inline-block");
						$('.id_input_msg1').css("display", "none");				
					}
				}
			});
		});
	
	});
</script>
</body>
</html>