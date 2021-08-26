<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>

<script type="text/javascript">
	$(document).ready(function(){
		let result = '<c:out value="${result}"/>';
		checkAlert(result);
	});
	
	function checkAlert(result) {
		if(result === ''){
            return;
        }
        
        if(result === "success"){
            alert("등록 성공!");
        }
	}
</script>

</head>
<body>
<h1>목록 페이지 입니다.</h1>
<a href="/board/enroll">게시글 등록</a>
</body>
</html>