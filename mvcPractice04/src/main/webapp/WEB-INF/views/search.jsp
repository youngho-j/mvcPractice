<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wellcome MyShop</title>
<link rel="stylesheet" href="/resources/css/search.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
		<div class="top_navi_area">
			<ul class="list">
				<c:if test="${member == null}">
					<li>
						<a href="/member/goLogin">로그인</a>
					</li>
					<li>
						<a href="/member/join">회원가입</a>
					</li>
				</c:if>
				<c:if test="${member != null}">
					<c:if test="${member.adminCk == 1 }">
						<li><a href="/admin/main">관리자 페이지</a></li>
					</c:if>
					<li>
						<a id="top_navi_logout_btn">로그아웃</a>
					</li>
					<li>
						<a href="">마이룸</a>
					</li>
					<li>
						<a href="">장바구니</a>
					</li>
				</c:if>
					<li>
						<a href="">고객센터</a>
					</li>
			</ul>
		</div>
		
		<div class="top_area">
			
			<div class="logo_area">
				<a href="/main">
					<img src="/resources/img/book2.png">
				</a>
			</div>
			
			<div class="search_area">
				<div class="searchForm_area">
                	<form id="searchForm" action="/search" method="get">
                		<div class="search_input">
                			<select name="type">
                				<option value="T">책 제목</option>
                				<option value="A">작가명</option>
                				<option value="C">카테고리 코드</option>
                			</select>
                			<input type="text" name="keyword">
                    		<button class='btn search_btn'>검 색</button>                				
                		</div>
                	</form>
                </div>
			</div>
			
			<div class="login_area">
				
				<c:if test="${member == null}">
					<div class="login_btn"><a href="/member/goLogin">로그인</a></div>
					<span><a href="/member/join">회원가입</a></span>
				</c:if>
				
				<c:if test="${member != null }">
					<div class="login_info">
						<span>회원 : ${member.memberName }</span>
						<span>충전금액 : <fmt:formatNumber value="${member.money }" pattern="#,###.## 원"/></span>
						<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,### 원"/></span>
						<span><a href="/member/logout">로그아웃</a></span>
					</div>
				</c:if>
				
			</div>
			
			<div class="clearfix"></div>
		</div>
		
		<div class="navi_area">
			<h1>navi area</h1>
		</div>
		
		<div class="content_area">
			<c:choose>
				<c:when test="${goodsListResult ne 'empty'}">
					<div class="goods_table_area">
						<table class="goods_table">
							<colgroup>
								<col width="110">
								<col width="*">
								<col width="120">
								<col width="120">
								<col width="120">
							</colgroup>
							<tbody>
								<c:forEach items="${goodsListResult}" var="list">
									<tr>
										<!-- 이미지 -->
										<td class="image">
										</td>
										<!-- 책 정보 -->
										<td class="book_info">
											<div class="category">
												[${list.categoryName}]
											</div>
											<div class="title">
												${list.bookName}
											</div>
											<div class="author">
												${list.authorName} 지음 | ${list.publisher} | ${list.publicationDate}
											</div>
										</td>
										<!--  -->
										<td class="rating_info">
											<div class="rating">
											</div>
										</td>
										<!-- 가격 -->
										<td class="price">
											<div class="origin_price">
												<del>
													${list.bookPrice}
												</del>
											</div>
											<div class="discount_price">
												<strong>
													<c:out value="${list.bookPrice * (1 - list.bookDiscount)}"/>
												</strong>
											</div>
										</td>
										<td class="option"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 페이징 추가 필요 -->
				</c:when>
				<c:otherwise>
					<div class="empty_table">
	                	검색 결과가 존재하지 않습니다.
	                </div>
				</c:otherwise>			
			</c:choose>
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
	$("#top_navi_logout_btn").click(function(){
		$.ajax({
			type:"POST",
			url:"/member/logout",
			success:function(data){
				document.location.reload();
			}
		});
	});
</script>
</body>
</html>