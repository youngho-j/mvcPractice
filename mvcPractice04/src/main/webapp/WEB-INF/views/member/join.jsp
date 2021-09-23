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
				<span class="final_id_check">아이디를 입력해주세요.</span>
			</div>
			
			<div class="pw_area">
				<div class="pw_title">비밀번호</div>
				<div class="pw_input_box">
					<input class="pw_input" name="memberPw">
				</div>
				<span class="final_pw_check">비밀번호를 입력해주세요.</span>
			</div>
			
			<div class="pw_check_area">
				<div class="pw_check_title">비밀번호 확인</div>
				<div class="pw_check_input_box">
					<input class="pw_check_input">
				</div>
				<span class="final_pw_doubleCheck">입력한 비밀번호를 다시 입력해주세요.</span>
				<span class="pw_doubleCheck_msg1">비밀번호가 일치합니다.</span>			
				<span class="pw_doubleCheck_msg2">비밀번호가 일치하지 않습니다.</span>			
			</div>
			
			<div class="user_area">
				<div class="user_title">이름</div>
				<div class="user_input_box">
					<input class="user_input" name="memberName">
				</div>
				<span class="final_name_check">이름을 입력해주세요.</span>
			</div>
			
			<div class="mail_area">
				<div class="mail_title">이메일</div> 
				<div class="mail_input_box">
					<input class="mail_input" name="memberMail">
				</div>
				<span class="final_mail_check">이메일을 입력해주세요.</span>
				<div class="mail_check_area">
					<div class="mail_check_input_box" id="mail_check_input_box_false">
						<input class="mail_check_input" disabled="disabled">
					</div>
					<div class="mail_check_btn">
						<span>인증번호 전송</span>
					</div>
					<div class="clearfix"></div>
					<span id="mail_check_messageBox"></span>
				</div>
			</div>
			
			<div class="address_area">
				<div class="address_title">주소</div>
				
				<div class="address_input_area1">
					<div class="address_input_box1">
						<input class="address_input1" name="memberAddr1" readonly="readonly">
					</div>
					<div class="address_btn" onclick="daum_address_open()">
						<span>주소 찾기</span>
					</div>
					<div class="clearfix"></div>
				</div>
				
				<div class ="address_input_area2">
					<div class="address_input_box2">
						<input class="address_input2" name="memberAddr2" readonly="readonly">
					</div>
				</div>
				
				<div class ="address_input_area3">
					<div class="address_input_box3">
						<input class="address_input3" name="memberAddr3" readonly="readonly">
					</div>
				</div>
				<span class="final_address_check">상세 주소를 입력해주세요.</span>
			</div>
			
			<div class="join_btn_area">
				<input type="button" class="join_btn" value="가입하기">
			</div>
			
		</div>
	</form>
</div>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	/* 회원가입 유효성 검사를 위한 변수 */
	var idCheck = false;
	var idDoubleCheck = false;
	
	var pwCheck = false;
	var pwDoubleCheck = false;
	
	var nameCheck = false;
	
	var mailCheck = false;
	var mailAuthCodeCheck = false;
	
	var addressCheck = false;
	
	/* 인증 코드 */
	var mailAuthCode = "";
	
	$(document).ready(function(){
		/* 회원가입 */
		$(".join_btn").click(function(){
			
			let id = $(".id_input").val();
			let pw = $(".pw_input").val();
			let pwConfirm = $(".pw_check_input").val();
			let name = $(".user_input").val();
			let mail = $(".mail_input").val();
			let addr = $(".address_input3").val();
			
			/* 아이디 유효성 검사 */
			if(id == "") {
				$(".final_id_check").css("display","block");
				idCheck = false;
			} else {
				$(".final_id_check").css("display","none");
				idCheck = true;				
			}
			
			/* 비밀번호 유효성 검사 */
			if(pw == "") {
				$(".final_pw_check").css("display","block");
				pwCheck = false;
			} else {
				$(".final_pw_check").css("display","none");
				pwCheck = true;				
			}
			
			/* 비밀번호 확인 유효성 검사 */
			if(pwConfirm == "") {
				$(".final_pw_doubleCheck").css("display","block");
				pwDoubleCheck = false;
			} else {
				$(".final_pw_doubleCheck").css("display","none");
				pwDoubleCheck = true;				
			}
			
			
			//$("#join_form").attr("action", "/member/join");
			//$("#join_form").submit(); */
			
		});		
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
					idDoubleCheck = true;
				} else {
					$('.id_input_msg2').css("display","inline-block");
					$('.id_input_msg1').css("display", "none");				
					idDoubleCheck = false;
				}
			}
		});
	});
		
	/* 인증번호 이메일 전송 */
	$(".mail_check_btn").click(function(){
			
		let email = $(".mail_input").val();
			
		/* 인증번호 입력 영역 */
		let boxArea = $(".mail_check_input_box");
			
		/* 인증번호 입력칸 */
		let authField = $(".mail_check_input");
			
		$.ajax({
	        type:"GET",
	        url:"mailCheck?email=" + email,
	        success:function(data) {
	        	authField.attr("disabled", false);
	        	boxArea.attr("id", "mail_check_input_box_true");
	        	mailAuthCode = data;
	        	console.log(mailAuthCode);
	        }
	    });
	});
		
	/* 인증 번호 비교 */
	$(".mail_check_input").blur(function(){
			
		/* 인증번호 입력칸에 작성된 값 */
		let authField = $(".mail_check_input").val();
			
		/* 인증 번호 비교 결과 */
		let contrastResult = $("#mail_check_messageBox");
			
		if(authField == mailAuthCode) {
			contrastResult.html("인증번호가 일치합니다.");
			contrastResult.attr("class", "correct");
		} else {
			contrastResult.html("인증번호가 일치하지 않습니다.");
			contrastResult.attr("class", "incorrect");
		}
	});
	
	/* 주소 검색 팝업창 */
	function daum_address_open() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수
 
                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }
 
                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 주소 변수에 참고항목 변수를 합친다.
                    addr += extraAddr;
                
                } else {
                    addr += ' ';
                }
 
                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $(".address_input1").val(data.zonecode);
                $(".address_input2").val(addr);
                
                // 읽기 전용 속성 해제 후 커서를 상세주소 필드로 이동한다.
                $(".address_input3").attr("readonly", false);
                $(".address_input3").focus();
                
			}
		}).open();
	}
	
	/* 비밀번호 일치여부 검사 */
	$(".pw_check_input").on("propertychange change keyup paste input", function(){
		let pw = $(".pw_input").val();
		let pwConfirm = $(".pw_check_input").val();
		
		/* 비밀번호 입력시 입력이 안되었다는 메세지 지우기 */
		$(".final_pw_doubleCheck").css("display", "none");
		
		if(pw == pwConfirm){
			$(".pw_doubleCheck_msg1").css("display","block");
			$(".pw_doubleCheck_msg2").css("display","none");
			pwDoubleCheck = true;
		} else {
			$(".pw_doubleCheck_msg1").css("display","none");
			$(".pw_doubleCheck_msg2").css("display","block");
			pwDoubleCheck = false;			
		}
	});
	
</script>
</body>
</html>