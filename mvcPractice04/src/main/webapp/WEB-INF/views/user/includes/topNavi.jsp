<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>

<!-- top_navi 영역 -->
<div class="top_navi_area">
	<ul class="list">
		<s:authorize access="isAnonymous()">
			<li>
				<a href="/goLogin">로그인</a>
			</li>
			<li>
				<a href="/join">회원가입</a>
			</li>
		</s:authorize>
		<s:authorize access="hasRole('ROLE_ADMIN')">
			<li>
				<a href="/admin/main">관리자 페이지</a>
			</li>
		</s:authorize>	
		<s:authorize access="hasRole('ROLE_MEMBER')">
			<li>
				<a id="top_navi_logout_btn">로그아웃</a>
			</li>
			<li>
				<a href="">마이룸</a>
			</li>
			<li>
				<a href="">장바구니</a>
			</li>
		</s:authorize>
		<li>
			<a href="">고객센터</a>
		</li>
	</ul>
</div>
