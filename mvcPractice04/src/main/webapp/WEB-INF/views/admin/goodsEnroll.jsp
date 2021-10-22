<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<!-- 물품 등록 페이지 css -->
<link rel="stylesheet" href="../resources/css/admin/goodsEnroll.css">
<!-- Datepicker css -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<!-- jquery CDN -->
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<!-- WYSYWYG_CKEditor5 -->
<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
<!-- Datepicker -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
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
                    <div class="admin_contents_title">
                    	<span>상품 등록</span>
                    </div>
                    <div class="admin_content_main">
                    	<form action="/admin/goodsEnroll" method="post" id="enrollForm">
                    		<!-- 책 제목 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 제목</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookName">
                    			</div>
                    		</div>
                    		
                    		<!-- 작가 아이디 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="authorId" value="0">
                    			</div>
                    		</div>
                    		
                    		<!-- 출판년도 입력 영역 -->            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>출판일</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="publicationDate" autocomplete="off" readonly="readonly">
                    			</div>
                    		</div>
                    		
                    		<!-- 출판사 입력 영역 -->            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>출판사</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="publisher">
                    			</div>
                    		</div>             
                    		
                    		<!-- 책 카테고리 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 카테고리</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="categoryCode">
                    			</div>
                    		</div>
                    		
                    		<!-- 가격 입력 영역 -->          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 가격</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookPrice" value="0">
                    			</div>
                    		</div>
                    		
                    		<!-- 재고 입력 영역 -->       
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 재고</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookStock" value="0">
                    			</div>
                    		</div>          
                    		
                    		<!-- 할인률 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 할인율</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookDiscount" value="0">
                    			</div>
                    		</div>          		
                    		
                    		<!-- 책 소개 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 소개</label>
                    			</div>
                    			<div class="form_section_content">
									<textarea name="bookIntro" id="bookIntro_textarea"></textarea>
                    			</div>
                    		</div>        		
                    		
                    		<!-- 책 목자 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 목차</label>
                    			</div>
                    			<div class="form_section_content">
									<textarea name="bookContents" id="bookContents_textarea"></textarea>
                    			</div>
                    		</div>
                   		</form>
                   		<!-- 버튼 영역 -->
                   		<div class="btn_section">
                   			<button id="cancelBtn" class="btn">취 소</button>
	                    	<button id="enrollBtn" class="btn enroll_btn">등 록</button>
	                    </div> 
                    </div>  
                </div>
                
                <div class="clearfix"></div>
			</div>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../includes/admin/footer.jsp" %>
	        
		</div>
	</div>
<script type="text/javascript">
	let enrollForm = $("#enrollForm");
	
	/* 취소 버튼 */
	$("#cancelBtn").click(function(){
	    location.href="/admin/goodsManage"
	});
	
	/* 상품 등록 버튼 */
	$("#enrollBtn").on("click",function(e){
		
		e.preventDefault();
		
		enrollForm.submit();
		
	});
	
	/* WYSYWYG_CKEditor5 적용 */
	
	ClassicEditor
		.create(document.querySelector('#bookIntro_textarea'))
		.catch(error => {
			console.error(error);
		});
	
	ClassicEditor
		.create(document.querySelector('#bookContents_textarea'))
		.catch(error => {
			console.error(error);
		});
	
	/* 캘린더 위젯 설정 */
	const config = {
			/* 출력 형식 */
			dateFormat : 'yy-mm-dd',
			
			/* 버튼 클릭 형식으로 변경 */
			showOn : 'button',
			buttonText : '날짜 선택',
			
			/* 캘린더 내부 오늘 날짜 및 닫기 패널 추가 */
			showButtonPanel : true,
			currentText: '오늘 날짜', 
	        closeText: '완료', 
			
			/* 캘린더 내부 한글 출력 변경 */
			prevText : '이전 달',
			nextText : '다음 달',
			changeMonth : true,
			changeYear : true,
			monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort : ['1','2','3','4','5','6','7','8','9','10','11','12'],
			dayNames : ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
			dayNamesMin : ['일','월','화','수','목','금','토']
	}
	/* 캘린더 위젯 */
	$(function() {
		$("input[name='publicationDate']").datepicker(config);
	});
	
</script>
</body>
</html>