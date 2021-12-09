<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- navi 영역 -->
<div class="navi_area">
	
	<div class="dropdown">
		<button class="dropdown_btn">국내 
			<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown_content">
			<c:forEach items="${domestic}" var="category"> 
		    	<a href="search?type=C&categoryCode=${category.categoryCode}">${category.categoryName}</a>
		    </c:forEach>
		</div>
	</div>
	
	<div class="dropdown">
		<button class="dropdown_btn">국외
			<i class="fa fa-caret-down"></i>
		</button>
		<div class="dropdown_content">
			<c:forEach items="${international}" var="category"> 
		    	<a href="search?type=C&categoryCode=${category.categoryCode}">${category.categoryName}</a>
		    </c:forEach>
		</div>
	</div>
	
</div>
