<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- top_navi 영역 -->
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
