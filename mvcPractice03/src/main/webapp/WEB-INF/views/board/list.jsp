<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>게시판</title>

<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>

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
	
	/* 글 동록 여부 출력 */
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
<div class="wrap">
	<div class="container">
		<div class="inner">
			<h1>게시판</h1>
			
		</div>
	</div>
</div>
<a href="/board/enroll">게시글 등록</a>
</body>
</html>