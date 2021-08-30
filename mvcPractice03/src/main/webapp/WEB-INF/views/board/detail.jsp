<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 상세 페이지</title>
<%
	String bno = request.getParameter("bno");
%>

<c:set var="bno" value="<%=bno %>"/>

<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>

<script type="text/javascript">

/*글 등록 페이지 이동*/
function goList() {         
    location.href = "/board/list";
}

</script>
</head>
<body>
<div id="wrap">
    <div id="container">
        <div class="inner">    
            <h2>상세 페이지</h2>
            <table width="100%" class="table01">
				<colgroup>
                	<col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <col width="*">
				</colgroup>
                	<tr>
                   		<th>제목</th>
                   		<td colspan="3"><c:out value="${detail.title}"/></td>
					</tr>
					<tr>
						<th>작성자</th>
                   		<td><c:out value="${detail.writer}"/></td>
						<th>작성일</th>
                   		<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${detail.regdate}" /></td>
					</tr>
					<tr>
						<th>내용</th>
                   		<td colspan="3"><c:out value="${detail.content}"/></td>
					</tr>
            </table>
            <div class="btn_right mt15">
                <button type="button" class="btn black mr5" onclick="javascript:goList();">목록으로</button>
                <button type="button" class="btn black mr5" onclick="javascript:goModify();">수정하기</button>
                <button type="button" class="btn black" onclick="javascript:delete();">삭제하기</button>
            </div>
		</div>
	</div>
</div>
</body>
</html>