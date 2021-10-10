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
			
			<!-- 최상단 네비 영역 -->
			<div class="top_navi_area">
				<ul class="list">    
                    <li><a href="/main">메인 페이지</a></li>
                    <li><a href="/member/logout">로그아웃</a></li>
                    <li>고객센터</li>            
                </ul>
			</div>
			
			<!-- 상단 제목 영역 -->
			<div class="top_area">
				<span>관리자 페이지</span>
			</div>
			
			<!-- 콘텐츠 네비 영역 -->
			<div class="admin_contents_navi_area">
				<div class="admin_contents_navi_list">
					<ul>
                        <li>
                        	<a class="admin_list_01" href="/admin/goodsEnroll">상품 등록</a>
                        </li>
                        <li>
                            <a class="admin_list_02" href="/admin/goodsManage">상품 관리</a>
                        </li>
                        <li>
                            <a class="admin_list_03" href="/admin/authorEnroll">작가 등록</a>                            
                        </li>
                        <li>
                            <a class="admin_list_04" href="/admin/authorManage">작가 관리</a>                            
                        </li>
                        <li>
                            <a class="admin_list_05">회원 관리</a>                            
                        </li>                                                                                             
                    </ul>
				</div>
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
                
                <div class="clearfix">
                </div>
			</div>
			
			<!-- footer_navi 영역 -->
			<div class="footer_navi_area">
	            <div class="footer_navi_list">
	                <ul>
	                    <li>회사소개</li>
	                    
	                    <li>이용약관</li>
	                    
	                    <li>고객센터</li>
	                    
	                    <li>광고문의</li>
	                    
	                    <li>채용정보</li>
	                </ul>
	            </div>
	        </div>
	        
	        <!-- footer 영역 -->
	        <div class="footer_area">
	            <div class="footer_info">
	                
	                <div class="footer_img_area">
	                    <img src="/resources/img/mbook2.png">
	                </div>
	                <div class="footer_explain">
	                    (주) My Book    대표이사 : OOO
	                    <br>
	                    사업자등록번호 : ooo-oo-ooooo
	                    <br>
	                    대표전화 : oooo-oooo(발신자 부담전화)
	                    <br>
	                    <br>
	                    COPYRIGHT(C) <strong>oooooo.ooooo.ooo</strong>    ALL RIGHTS RESERVED.
	                </div>
	                <div class="clearfix"></div>
	            </div>
	        </div>
	        
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