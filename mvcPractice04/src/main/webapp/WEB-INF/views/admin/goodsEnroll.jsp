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
                    				<span class="msg bookName_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 작가 아이디 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="input_authorName" readonly="readonly">
                    				<input id="input_authorId" name="authorId" type="hidden">
                    				<button class="authorSelect">작가 선택</button>
                    				<span class="msg authorId_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 출판년도 입력 영역 -->            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>출판일</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="publicationDate" autocomplete="off" readonly="readonly">
                    				<span class="msg publicationDate_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 출판사 입력 영역 -->            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>출판사</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="publisher">
                    				<span class="msg publisher_msg"></span>
                    			</div>
                    		</div>             
                    		
                    		<!-- 책 카테고리 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 카테고리</label>
                    			</div>
                    			<div class="form_section_content">
                    				<div class="category_area">
                    					<span>대분류</span>
                    					<select class="main_category">
                    						<option selected value="none">선택</option>
                    					</select>
                    				</div>
                    				<div class="category_area">
                    					<span>중분류</span>
                    					<select class="middle_category">
                    						<option selected value="none">선택</option>
                    					</select>
                    				</div>
                    				<div class="category_area">
                    					<span>소분류</span>
                    					<select class="sub_category" name="categoryCode">
                    						<option selected value="none">선택</option>
                    					</select>
                    				</div>
 	                   				<span class="msg categoryCode_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 가격 입력 영역 -->          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 가격</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookPrice" value="0">
                    				<span class="msg bookPrice_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 재고 입력 영역 -->       
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 재고</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookStock" value="0">
                    				<span class="msg bookStock_msg"></span>
                    			</div>
                    		</div>          
                    		
                    		<!-- 할인률 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 할인율</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="discount_viewer" maxlength="2" value="0">
                    				<input name="bookDiscount" type="hidden" value="0">
                    				<span class="discountValue_area">할인 가격 : <span class="convertPrice"></span></span>
                    				<span class="msg bookDiscount_msg"></span>
                    			</div>
                    		</div>          		
                    		
                    		<!-- 책 소개 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 소개</label>
                    			</div>
                    			<!-- 공란 검사를 위해 class 추가 -->
                    			<div class="form_section_content bi">
									<textarea name="bookIntro" id="bookIntro_textarea"></textarea>
									<span class="msg bookIntro_msg"></span>
                    			</div>
                    		</div>        		
                    		
                    		<!-- 책 목자 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 목차</label>
                    			</div>
                    			<div class="form_section_content bc">
									<textarea name="bookContents" id="bookContents_textarea"></textarea>
									<span class="msg bookContents_msg"></span>
                    			</div>
                    		</div>
                    		
                    		<!-- 이미지 추가 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 이미지</label>
                    			</div>
                    			<div class="form_section_content">
									<input type="file" id ="fileItem" name="uploadFile">
									<div id="uploadImg">
									</div>
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
		
		/* 유효성 검사를 위한 변수 */
		let bookNameCk = false;
		let authorIdCk = false;
		let publicationDateCk = false;
		let publisherCk = false;
		let categoryCodeCk = false;
		let bookPriceCk = false;
		let bookStockCk = false;
		let bookDiscountCk = false;
		let bookIntroCk = false;
		let bookContentsCk = false;
		
		/* 입력란 변수*/
		let bookName = $("input[name='bookName']").val();
		let authorId = $("input[name='authorId']").val();
		let publicationDate = $("input[name='publicationDate']").val();
		let publisher = $("input[name='publisher']").val();
		let categoryCode = $("select[name='categoryCode']").val();
		let bookPrice = $("input[name='bookPrice']").val();
		let bookDiscount = $("#discount_viewer").val();
		let stock = $("input[name='bookStock']").val();
			/* .html() 해당 요소 안의 내용을 가져옴 */
		let bookIntro = $(".bi p").html();
		let bookContents = $(".bc p").html();
		
		/* 공백 검사 */
		if(!bookName) {
			$(".bookName_msg").html('책 이름을 입력해주세요.');
			$(".bookName_msg").css('display','block');
			bookNameCk = false;
		} else {
			$(".bookName_msg").html('');
			$(".bookName_msg").css('display','none');
			bookNameCk = true;
		}
		
		if(!authorId) {
			$(".authorId_msg").html('작가를 선택해주세요.');
			$(".authorId_msg").css('display','block');
			authorIdCk = false;
		} else {
			$(".authorId_msg").html('');
			$(".authorId_msg").css('display','none');
			authorIdCk = true;
		}
		
		if(!publicationDate) {
			$(".publicationDate_msg").html('출판일을 선택해 주세요.');
			$(".publicationDate_msg").css('display', 'block');
			publicationDateCk = false;
		} else {
			$(".publicationDate_msg").html('');
			$(".publicationDate_msg").css('display', 'none');
			publicationDateCk = true;
		}
		
		if(!publisher){
			$(".publisher_msg").html('출판사를 입력해주세요.');
			$(".publisher_msg").css('display','block');
			publisherCk = false;
		} else {
			$(".publisher_msg").html('');
			$(".publisher_msg").css('display','none');
			publisherCk = true;
		}
		
		if(categoryCode == 'none'){
			$(".categoryCode_msg").html('카테고리를 선택해주세요.');
			$(".categoryCode_msg").css('display','block');
			categoryCodeCk = false;
		} else {
			$(".categoryCode_msg").html('');
			$(".categoryCode_msg").css('display','none');
			categoryCodeCk = true;
		}
		
		if(bookPrice > 0 && bookPrice != '' && bookPrice.charAt(0) != '-' && bookPrice.indexOf('.') == -1) {
			$(".bookPrice_msg").html('');
			$(".bookPrice_msg").css('display','none');
			bookPriceCk = true;
		} else {
			$(".bookPrice_msg").html('상품 가격을 입력해주세요.');
			$(".bookPrice_msg").css('display','block');
			bookPriceCk = false;
		}

		if(stock >= 0 && stock != '' && stock.charAt(0) != '-' && stock.indexOf('.') == -1){
			$(".bookStock_msg").html('');
			$(".bookStock_msg").css('display','none');
			bookStockCk = true;
		} else {
			$(".bookStock_msg").html('상품 재고를 입력해주세요.');
			$(".bookStock_msg").css('display','block');
			bookStockCk = false;
		}
		
		if(!isNaN(bookDiscount)){
			$(".bookDiscount_msg").html('');
			$(".bookDiscount_msg").css('display','none');
			bookDiscountCk = true;
		} else {
			$(".bookDiscount_msg").html('상품 할인율을 입력해주세요.');
			$(".bookDiscount_msg").css('display','block');
			bookDiscountCk = false;
		}	
		
		if(bookIntro == '<br data-cke-filler="true">'){
			$(".bookIntro_msg").html('책 소개를 입력해주세요.');
			$(".bookIntro_msg").css('display','block');
			bookIntroCk = false;
		} else {
			$(".bookIntro_msg").html('');
			$(".bookIntro_msg").css('display','none');
			bookIntroCk = true;
		}
		
		if(bookContents == '<br data-cke-filler="true">'){
			$(".bookContents_msg").html('책 목차를 입력해주세요.');
			$(".bookContents_msg").css('display','block');
			bookContentsCk = false;
		} else {
			$(".bookContents_msg").html('');
			$(".bookContents_msg").css('display','none');
			bookContentsCk = true;
		}
		
		console.log("재고 : " + bookStockCk);
		console.log("재고 입력 값 : " + stock);
		
		/* 최종 확인 */
		if(bookNameCk && authorIdCk && publicationDateCk && publisherCk 
				&& categoryCodeCk && bookPriceCk && bookStockCk && bookDiscountCk 
				&& bookIntroCk && bookContentsCk) {
			enrollForm.submit();			
		} else {
			return false;
		}
		
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
	
	/* 작가 선택 팝업창 */
	$('.authorSelect').on("click",function(e){
		
		e.preventDefault();
		
		let url = "/admin/authorSearch";
		let option = "width = 650px, height=550px, top=300px, left=300px, scrollbars=yes";
		
		window.open(url, "작가 검색", option);
	});
	
	/* 카테고리 목록 */
	let categoryList = JSON.parse('${categoryList}');
	
	/* 카테고리 정보를 담을 객체 변수 */
	let categoryObject = new Object(); 
	
	/* 카테고리 정보 객체 저장 배열 */
	let categoryArray1 = new Array();
	let categoryArray2 = new Array();
	let categoryArray3 = new Array();
	
	/* select 태그 접근 변수 */
	let mainSelected = $(".main_category");
	let middleSelected = $(".middle_category");
	let subSelected = $(".sub_category");
	
	/* 배열에 객체 주입 */
	function makeArray(object, array, name, code, parent) {
		object.categoryName = name;
		object.categoryCode = code;
		object.parentCategory = parent;
		
		array.push(object);
	}
	
	/* 카테고리 배열 초기화 */
	function returnCategoryArray(categoryList, object, array1, array2, array3) {
		for(let i = 0 ; i < categoryList.length ; i++) {
			object = new Object();
			switch(categoryList[i].tier) {
				case 1 :
					makeArray(object, array1, categoryList[i].categoryName, categoryList[i].categoryCode, categoryList[i].parentCategory);
					break;
				case 2 :
					makeArray(object, array2, categoryList[i].categoryName, categoryList[i].categoryCode, categoryList[i].parentCategory);
					break;
				case 3 :
					makeArray(object, array3, categoryList[i].categoryName, categoryList[i].categoryCode, categoryList[i].parentCategory);
					break;
			}
		}
	}
	
	returnCategoryArray(categoryList, categoryObject, categoryArray1, categoryArray2, categoryArray3);
	
	/* 카테고리 대분류 */
	for(let i = 0 ; i < categoryArray1.length ; i++) {
		mainSelected.append("<option value='" + categoryArray1[i].categoryCode + "'>" + categoryArray1[i].categoryName + "</option>");
	}
	
	/* 카테고리 중분류 */
	$(mainSelected).on("change", function(){
		/* 대분류에서 선택된 값 */
		let mainSelectedValue = $(this).find("option:selected").val();
		
		/* 대분류 재선택시 기존 출력되는 중, 소분류 option 태그 지우기 */
		middleSelected.children().remove();
		subSelected.children().remove();
		
		middleSelected.append("<option value='none'>선택</option>");
		subSelected.append("<option value='none'>선택</option>");
		
		for(let i = 0 ; i < categoryArray2.length ; i++) {
			if(mainSelectedValue === categoryArray2[i].parentCategory) {
				middleSelected.append("<option value='" + categoryArray2[i].categoryCode + "'>" + categoryArray2[i].categoryName + "</option>");
			}
		}
	});
	
	/* 카테고리 소분류 */
	$(middleSelected).on("change", function(){
		/* 대분류에서 선택된 값 */
		let middleSelectedValue = $(this).find("option:selected").val();
		
		/* 중분류 재선택시 기존 출력되는 소분류 option 태그 지우기 */
		subSelected.children().remove();
		
		subSelected.append("<option value='none'>선택</option>");
		
		for(let i = 0 ; i < categoryArray3.length ; i++) {
			if(middleSelectedValue === categoryArray3[i].parentCategory) {
				subSelected.append("<option value='" + categoryArray3[i].categoryCode + "'>" + categoryArray3[i].categoryName + "</option>");
			}
		}
	});
	
	/* 할인율 변환 및 가격 출력 */
	$("#discount_viewer").on("propertychange change keyup paste input", function(){
		/* 상품 원가 */
		let price = $("input[name='bookPrice']").val();
		
		/* 사용자 입력 값 */
		let userInput = $("#discount_viewer").val();
		
		/* 할인율 변환 값 */
		let convertDiscount = userInput / 100;
		
		/* 변환되는 할인율을 담을 태그 */
		let saveDiscount = $("input[name='bookDiscount']");
		
		let discountPrice = Math.floor(price * (1 - convertDiscount) / 10) * 10;
		
		$(".convertPrice").html("");
		
		if(!isNaN(userInput)) {
			$(".convertPrice").html(discountPrice);
			saveDiscount.val(convertDiscount);
		}
	});
	
	/* 상품 가격 변동 되는 경우 가격 출력 */
	$("input[name='bookPrice']").on("change", function(){
		
		let userInput = $("input[name='bookPrice']").val();
		
		let saveDiscount = $("input[name='bookDiscount']").val();
		
		$(".convertPrice").html("");
		
		/* 할인율 변환 값 */
		if(!isNaN(userInput)) {
			
			let discountPrice = Math.floor(userInput * (1 - saveDiscount) / 10) * 10;
			
			$(".convertPrice").html(discountPrice);			
		}
	});
	
	/* 이미지 업로드 */
	$("input[type='file']").on("change", function(e){
		
		let imgDeleteBtn = $(".imgDeleteBtn").length;
		
		/* 기존 업로드 된 이미지가 있을 경우 삭제 */
		if(imgDeleteBtn > 0) {
			deleteImageFile();
		}
		
		/* 이미지 정보를 보내기 위해 객체 선언*/
		let formData = new FormData();
		
		let uploadFile = $('input[name="uploadFile"]');
		let fileList = uploadFile[0].files;
		let fileObj = fileList[0];
		
		if(!fileCheck(fileObj.name, fileObj.size)) {
			return false;
		}
		
		formData.append("uploadFile", fileObj);
		
		$.ajax({
			url : '/admin/ajaxUpload',
			processData : false,
			contentType : false,
			type : 'POST',
			data : formData,
			dataType : 'json',
			success : function(result) {
				console.log(result);
				showImage(result);
			},
			error : function(result) {
				alert("해당 파일은 이미지 파일형식이 아닙니다.");				
			}
		});
	});
	
	/* 이미지 업로드 규칙 설정 */	
	function fileCheck(fileName, fileSize) {
		
		/* jpg, png 파일만 허용 */
		let regex = new RegExp("(.*?)\.(jpg|png)$");
		/* 업로드 파일 크기 최대 1MB */
		let maxFileSize = 1048567;
		
		if(fileSize >= maxFileSize){
			alert(`업로드가 가능한 파일크기가 아닙니다.
[최대 1MB까지 업로드 가능합니다.]`);
			return false;
		}
			  
		if(!regex.test(fileName)){
			alert(`업로드 가능한 파일확장자가 아닙니다.
[jpg, png 파일만 업로드 가능합니다.]`);
			return false;
		}
		
		return true;
		
	}
	
	/* 썸네일 출력 영역 생성 및 썸네일 미리보기 */
	function showImage(uploadResult) {
		/* 데이터 검증 */
		if(!uploadResult || uploadResult.length == 0) {return}
		
		let uploadImg = $("#uploadImg");
		
		let fileObj = uploadResult[0];
		
		/*  현재 파일 객체의 uploadPath 변수에는 
			고정경로와 변동 경로가 합쳐져 있으므로 substr 함수를 통해 문자열 분리 
			형식 - 년\월\일\t_UUID_파일이름.파일확장자
		*/
		let variationRoot = fileObj.uploadPath.substr(23);
		
		let fileName = 
			encodeURIComponent(variationRoot + "\\t_" + fileObj.uuid + "_" + fileObj.fileName);
		
		/* 이미지 영역 */
		let imgArea = "";
		
		imgArea += "<div id='img_area'>";
		imgArea += "<img src='/display?fileName=" + fileName +"'>";
		imgArea += "<div class='imgDeleteBtn' data-file='" + fileName + "'>×</div>";
		imgArea += "</div>";
		
		uploadImg.append(imgArea);
	}
	
	/* 버튼 클릭시 이미지 파일 삭제 콜백 */
	$("#uploadImg").on("click", ".imgDeleteBtn", function(e){
		deleteImageFile();
	});
	
	/* 이미지 파일 삭제 메서드 */
	function deleteImageFile() {
		let file = $(".imgDeleteBtn").data("file");
		
		let imgArea = $("#img_area");
		
		$.ajax({
			url : '/admin/delUploadImg',
			data : {fileName : file},
			dataType : 'text',
			type : 'POST',
			success : function(result) {
				imgArea.remove();
				$("input[type='file']").val("");
			},
			error : function(result) {
				alert("해당 파일을 삭제하지 못했습니다.")
			}
		});
	}
</script>
</body>
</html>