<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<!-- 물품 등록 페이지 css -->
<link rel="stylesheet" href="../resources/css/admin/goodsDetail.css">
<!-- jquery CDN -->
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
<!-- WYSYWYG_CKEditor5 -->
<script src="https://cdn.ckeditor.com/ckeditor5/30.0.0/classic/ckeditor.js"></script>
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
                    	<span>상품 상세</span>
                    </div>
                    <div class="admin_content_main">
                    		<!-- 책 제목 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 제목</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookName" value="<c:out value="${goodsDetail.bookName}"/>" disabled="disabled">
                    			</div>
                    		</div>
                    		
                    		<!-- 등록일자 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>등록 일자</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input value="<fmt:formatDate value='${goodsDetail.regDate}' pattern='yyyy-MM-dd'/>" disabled="disabled"> 
                    			</div>
                    		</div>
                    		
                    		<!-- 최근 수정 일자 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>최근 수정 일자</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input value="<fmt:formatDate value='${goodsDetail.updateDate}' pattern='yyyy-MM-dd'/>" disabled="disabled"> 
                    			</div>
                    		</div>
                    		
                    		<!-- 작가 이름영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>작가</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="input_authorName" value="<c:out value="${goodsDetail.authorName}"/>" readonly="readonly">
                    			</div>
                    		</div>
                    		
                    		<!-- 출판일 영역 -->            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>출판일</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="publicationDate" value="<c:out value="${goodsDetail.publicationDate}"/>" autocomplete="off" readonly="readonly" disabled="disabled">
                    			</div>
                    		</div>
                    		
                    		<!-- 출판사 영역 -->            
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>출판사</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="publisher" value="<c:out value="${goodsDetail.publisher}"/>" disabled="disabled">
                    			</div>
                    		</div>             
                    		
                    		<!-- 책 카테고리 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 카테고리</label>
                    			</div>
                    			<div class="form_section_content">
                    				<div class="category_area">
                    					<span>대분류</span>
                    					<select class="main_category" disabled="disabled">
                    						<option value="none">선택</option>
                    					</select>
                    				</div>
                    				<div class="category_area">
                    					<span>중분류</span>
                    					<select class="middle_category" disabled="disabled">
                    						<option value="none">선택</option>
                    					</select>
                    				</div>
                    				<div class="category_area">
                    					<span>소분류</span>
                    					<select class="sub_category" name="categoryCode" disabled="disabled">
                    						<option value="none">선택</option>
                    					</select>
                    				</div>
                    			</div>
                    		</div>
                    		
                    		<!-- 가격 영역 -->          
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 가격</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookPrice" value="<c:out value="${goodsDetail.bookPrice}"/>" disabled="disabled">
                    			</div>
                    		</div>
                    		
                    		<!-- 재고 영역 -->       
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 재고</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input name="bookStock" value="<c:out value="${goodsDetail.bookStock}"/>" disabled="disabled">
                    			</div>
                    		</div>          
                    		
                    		<!-- 할인율 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>상품 할인율</label>
                    			</div>
                    			<div class="form_section_content">
                    				<input id="discount_viewer" maxlength="2" disabled="disabled">
                    			</div>
                    		</div>          		
                    		
                    		<!-- 책 소개 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 소개</label>
                    			</div>
                    			<div class="form_section_content bi">
									<textarea name="bookIntro" id="bookIntro_textarea" disabled="disabled">${goodsDetail.bookIntro}</textarea>
                    			</div>
                    		</div>        		
                    		
                    		<!-- 책 목자 입력 영역 -->
                    		<div class="form_section">
                    			<div class="form_section_title">
                    				<label>책 목차</label>
                    			</div>
                    			<div class="form_section_content bc">
									<textarea name="bookContents" id="bookContents_textarea" disabled="disabled">${goodsDetail.bookContents}</textarea>
                    			</div>
                    		</div>
                   		<!-- 버튼 영역 -->
                   		<div class="btn_section">
                   			<button id="cancelBtn" class="btn">상품 목록</button>
	                    	<button id="enrollBtn" class="btn enroll_btn">수 정</button>
	                    </div>
	                    
	                    <!-- 페이지 이동 form -->
	                    <form id="moveForm" action="/admin/goodsManage" method="get">
							<input type="hidden" name="pageNum" value="${pagingManager.pageInfo.pageNum}">
							<input type="hidden" name="viewPerPage" value="${pagingManager.pageInfo.viewPerPage}">
							<input type="hidden" name="keyword" value="${pagingManager.pageInfo.keyword}">
						</form>
						 
                    </div>  
                </div>
                
                <div class="clearfix"></div>
			</div>
			
			<!-- 최하단 네비, footer 영역 -->
			<%@include file="../includes/admin/footer.jsp" %>
	        
		</div>
	</div>
<script type="text/javascript">
	$(document).ready(function(){
		/* 할인율 */
		let bookDiscount = '<c:out value="${goodsDetail.bookDiscount}"/>' * 100;
		
		$("#discount_viewer").attr("value", bookDiscount);
		
		/* 카테고리 데이터 */
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
		
		/* 카테고리 소분류 코드 값 */
		let subCategoryCode = ${goodsDetail.categoryCode};
		
		/* 저장된 카테고리 코드값과 일치하는 카테고리 정보 */
		let subSelectedValue = new Object();
		let middleSelectedValue = new Object();
		let mainSelectedValue = new Object();
		
		/* 선택된 항목 데이터 객체로 변환, 파라미터 : 각티어별로 분류된 배열, 비교하고자 하는 카테고리 값, 값을 담아줄 객체 */
		function returnCategoryObject(array, categoryData, categoryInfoObject) {
			for(let i = 0 ; i < array.length ; i++) {
				/* === 를 사용할 경우 데이터 타입이 달라서 false 출력 */
				if(categoryData == array[i].categoryCode) {
					/* 변수에 배열을 넣어 실행시켜봤지만 undifined 출력 */
					categoryInfoObject.categoryCode = array[i].categoryCode;
					categoryInfoObject.categoryName = array[i].categoryName;
					categoryInfoObject.parentCategory = array[i].parentCategory;
				}
			}
		}
		
		/* 카테고리 목록 추가, 파라미터 : 각 티어별로 분류된 배열, 값을 담은 객체, 옵션을 추가하고자 하는 태그 */
		function appendCategory(array, categoryInfoObject, selectedTag) {
			for(let i = 0 ; i < array.length ; i++) {
				if(categoryInfoObject.parentCategory === array[i].parentCategory) {
					if(categoryInfoObject.categoryCode === array[i].categoryCode) {
						selectedTag.append("<option selected value='" + array[i].categoryCode + "'>" + array[i].categoryName + "</option>");
						return;
					}
					selectedTag.append("<option value='" + array[i].categoryCode + "'>" + array[i].categoryName + "</option>");
				}
			}
		}
		
		returnCategoryArray(categoryList, categoryObject, categoryArray1, categoryArray2, categoryArray3);
		
		/* 소분류 카테고리 설정 */
		returnCategoryObject(categoryArray3, subCategoryCode, subSelectedValue);
		
		appendCategory(categoryArray3, subSelectedValue, subSelected);
		
		/* 중분류 카테고리 설정 */
		returnCategoryObject(categoryArray2, subSelectedValue.parentCategory, middleSelectedValue);
		
		appendCategory(categoryArray2, middleSelectedValue, middleSelected);
		
		/* 대분류 카테고리 설정 */
		returnCategoryObject(categoryArray1, middleSelectedValue.parentCategory, mainSelectedValue);
		
		appendCategory(categoryArray1, mainSelectedValue, mainSelected);
	});
	
	/* WYSYWYG_CKEditor5 적용 */
		/* 책 소개 수정 방지 */
	ClassicEditor
		.create(document.querySelector('#bookIntro_textarea'))
		.then(editor => {
			console.log(editor);
			editor.isReadOnly = true;
		})
		.catch(error => {
			console.error(error);
		});
	
		/* 책 목차 수정 방지 */
	ClassicEditor
		.create(document.querySelector('#bookContents_textarea'))
		.then(editor => {
			console.log(editor);
			editor.isReadOnly = true;
		})
		.catch(error => {
			console.error(error);
		});
	
</script>
</body>
</html>