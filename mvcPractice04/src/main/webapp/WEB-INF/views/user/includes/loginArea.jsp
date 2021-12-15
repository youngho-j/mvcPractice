<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 로그인 영역 -->
<div class="login_area">
	
	<!-- 로그인 X -->
	<c:if test="${member == null}">
		<div class="login_btn"><a href="/goLogin">로그인</a></div>
		<span>
			<a href="/join">회원가입</a>
		</span>
	</c:if>
				
	<!-- 로그인 성공시 -->		
	<c:if test="${member != null }">
		<div class="login_info">
			<span>회원 : ${member.memberName }</span>
			<span>충전금액 : <fmt:formatNumber value="${member.money }" pattern="#,###.## 원"/></span>
			<span>포인트 : <fmt:formatNumber value="${member.point }" pattern="#,### 원"/></span>
			<span><a href="/logout">로그아웃</a></span>
		</div>
	</c:if>
					
</div>