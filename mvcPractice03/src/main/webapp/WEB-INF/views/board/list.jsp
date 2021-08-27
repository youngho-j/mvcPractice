<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	/*글 등록 페이지 이동*/
	function goEnroll() {         
        location.href = "/board/enroll";
    }
	
</script>

</head>
<body>
<div id="wrap">
	<div id="container">
		<div class="inner">
			<h1>게시판</h1>
			<table width="100%" class="table01">
            	<colgroup>
                	<col width="10%" />
                    <col width="25%" />
                    <col width="10%" />
                    <col width="15%" />
                    <col width="20%" />
                </colgroup>
                <thead>        
                	<tr>
                    	<th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>수정일</th>
                   </tr>
                </thead>
                <c:forEach items="${list}" var="list">
			    	<tr>
			        	<td><c:out value="${list.bno}"/></td>
			            <td><c:out value="${list.title}"/></td>
			            <td><c:out value="${list.writer}"/></td>
			            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${list.regdate}" /></td>
			            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${list.updateDate}" /></td>
			        </tr>
			    </c:forEach>   
            </table>
            <div class="btn_right mt15">
                <button type="button" class="btn black mr5" onclick="javascript:goEnroll();">게시글 작성</button>
            </div>
		</div>
	</div>
</div>
</body>
</html>