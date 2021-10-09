<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/authorManage.css">
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
                    <div class="admin_contents_title"><span>작가 관리</span></div>
                </div>
                <div class="clearfix"></div>
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
$(document).ready(function(){
    
    let result = '<c:out value="${enroll_result}"/>';
    
    checkResult(result);
    
});
function checkResult(result){
	if(result === ''){
    	return;
    }
    alert("작가'${enroll_result}' 을 등록하였습니다.");
}
</script>
</body>
</html>