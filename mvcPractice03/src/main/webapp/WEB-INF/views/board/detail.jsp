<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 상세 페이지</title>

<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>

<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
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
            <form id="infoForm" method="get">
	            <input type="hidden" id="bno"         name="bno"          value="${detail.bno}"/>
	            <input type="hidden" id="curPageNum"  name="curPageNum"   value="${pagingModel.curPageNum}"/>
	            <input type="hidden" id="viewPerPage" name="viewPerPage"  value="${pagingModel.viewPerPage}"/>
	            <input type="hidden" id="keyword"     name="keyword"      value="${pagingModel.keyword}">
	            <input type="hidden" id="keyword"     name="type"      value="${pagingModel.type}">
            </form>
            <div class="btn_right mt15">
                <button type="button" class="btn black mr5" onclick="javascript:goList();">목록으로</button>
                <button type="button" class="btn black mr5" onclick="javascript:goModify();">수정하기</button>
                <button type="button" class="btn black" onclick="javascript:deletePost();">삭제하기</button>
            </div>
		</div>
	</div>
</div>
<script type="text/javascript">

var infoForm = $("#infoForm");

/*글 목록 페이지 이동*/
function goList() {
	infoForm.find("#bno").remove();
	infoForm.attr("action", "/board/list");
	infoForm.submit();
}

/* 글 삭제 */
function deletePost() {
	let bno = $("#bno").val();
    
    var yn = confirm("게시글을 삭제하시겠습니까?");
    
    if(yn) {
    	location.href = "/board/delete?bno=" + bno;
    }
    
}

/*글 수정 페이지 이동*/
function goModify() {
	infoForm.attr("action", "/board/modify");
	infoForm.submit();	
}
</script>
</body>
</html>