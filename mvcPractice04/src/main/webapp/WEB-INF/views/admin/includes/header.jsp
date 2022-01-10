<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 최상단 네비 영역 -->
	<div class="top_navi_area">
		<ul class="list">    
    		<li><a href="/main">메인 페이지</a></li>
            <li>
            	<a href="#" onclick="document.getElementById('logout-form').submit();">로그아웃</a>
            </li>
            <li>고객센터</li>            
        </ul>
        <form id="logout-form" action="/logout" method="POST">
			<input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
		</form>
	</div>
			
	<!-- 상단 제목 영역 -->
	<div class="top_area">
		<span>관리자 페이지</span>
	</div>