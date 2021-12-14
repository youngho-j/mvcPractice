<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/authorEnroll.css">
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
                    
                    <div class="admin_contents_title">
                    	<span>작가 등록</span>
                    </div>
                    
                    <div class="admin_contents_main">
                    	<form action="/admin/authorEnroll" method="post" id="enrollForm">
                    		<!-- 작가 이름 입력-->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가 이름</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="authorName">
                    				<span id="authorName_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 소속 국가 선택-->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>소속 국가</label>
                    			</div>
                    			<div class="form_section_content">
                    				<select name="nationId">
                    					<option value="none" selected>=== 선택 ===</option>
                    					<option value="01">국  내</option>
                    					<option value="02">국  외</option>
                    				</select>
                    				<span id="nationId_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 작가 소개 입력 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가 소개</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="authorProfile" type="text">
                    				<span id="authorProfile_msg"></span>
                    			</div>
                    		</div>
                    	</form>
                    	<!-- 등록 버튼 -->
                    	<div class="btn_section">
                   				<button id="cancelBtn" class="btn">취 소</button>
	                    		<button id="enrollBtn" class="btn enroll_btn">등 록</button>
	                    </div>
	                </div>
                </div>
                
                <div class="clearfix"></div>
			</div>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../admin/includes/footer.jsp" %>
	        
		</div>
	</div>
<script type="text/javascript">
	/* 등록 버튼 */
	$("#enrollBtn").click(function(){
		
		/* 유효성 체크 변수 */ 
		let nameCheck = false;
		let nationCheck = false;
		let profileCheck = false;
		
		let authorName = $('input[name=authorName]').val();
	    let nationId = $('select[name=nationId]').val();
	    let authorProfile = $('input[name=authorProfile]').val(); 
	    
	    /* span 영역 변수 */
	    let authorNameMsg = $('#authorName_msg');
	    let nationIdMsg = $('#nationId_msg');
	    let authorProfileMsg = $('#authorProfile_msg');
		
	    /* 작가 이름 공란 체크 */
	    if(authorName === '') {
	    	authorNameMsg.html("작가 이름을 입력해주세요.");
	    	authorNameMsg.css('color', 'red');
	    	authorNameMsg.css('display', 'block');
	    	nameCheck = false;
	    } else {
	    	authorNameMsg.css('display', 'none');
	    	nameCheck = true;
	    }
	    
	    /* 소속 국가 공란 체크 */
	    if(nationId === 'none') {
	    	nationIdMsg.html("소속 국가를 입력해주세요.");
	    	nationIdMsg.css('color', 'red');
	    	nationIdMsg.css('display', 'block');
	    	nationCheck = false;
	    } else {
	    	nationIdMsg.css('display', 'none');
	    	nationCheck = true;
	    }
	    
	    /* 작가 소개 공란 체크 */
	    if(authorProfile === '') {
	    	authorProfileMsg.html("작가 소개를 입력해주세요.");
	    	authorProfileMsg.css('color', 'red');
	    	authorProfileMsg.css('display', 'block');
	    	profileCheck = false;
	    } else {
	    	authorProfileMsg.css('display', 'none');
	    	profileCheck = true;
	    }
	    if(nameCheck && nationCheck && profileCheck) {
		    $("#enrollForm").submit();	    	
	    } else {
	    	return;
	    }
	});
	 
	/* 취소 버튼 */
	$("#cancelBtn").click(function(){
	    location.href="/admin/authorManage"
	});
</script>
</body>
</html>