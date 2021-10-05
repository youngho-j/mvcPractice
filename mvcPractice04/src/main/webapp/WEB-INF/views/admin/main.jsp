<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link rel="stylesheet" href="../resources/css/admin/main.css">
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
                        	<a class="admin_list_01">상품 등록</a>
                        </li>
                        <li>
                            <a class="admin_list_02">상품 목록</a>
                        </li>
                        <li>
                            <a class="admin_list_03">작가 등록</a>                            
                        </li>
                        <li>
                            <a class="admin_list_04">작가 관리</a>                            
                        </li>
                        <li>
                            <a class="admin_list_05">회원 관리</a>                            
                        </li>                                                                                             
                    </ul>
				</div>
				<div class="admin_contents_area">
                    <div>관리자 페이지 입니다.</div>
                </div>
                <div class="clearfix"></div>
			</div>
		</div>
	</div>
</body>
</html>