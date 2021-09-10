<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정 페이지</title>
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
            <h2>게시글 수정 페이지</h2>
            <form action="/board/modify" method="post" onsubmit="return nullCheck()">    
                <table width="100%" class="table02">
                <caption><strong><span class="t_red">*</span> 표시는 필수입력 항목입니다.</strong></caption>
                    <colgroup>
                         <col width="20%">
                         <col width="*">
                    </colgroup>
                    <tbody id="tbody">
                       <tr>
                            <th>제목<span class="t_red">*</span></th>
                            <td><input id="title" name="title" value="${before.title}" class="tbox01"/></td>
                        </tr>
                         <tr>
                            <th>작성자</th>
                            <td><c:out value="${before.writer}"/></td>
                        </tr>
                        <tr>
                            <th>내용<span class="t_red">*</span></th>
                            <td colspan="3"><textarea id="content" name="content" cols="50" rows="5" class="textarea01"><c:out value="${before.content}"/></textarea></td>
                        </tr>
                    </tbody>
                </table>    
                <input type="hidden" id="bno" name="bno" value="${before.bno}"/>
            <div class="btn_right mt15">
                <button type="button" class="btn black" onclick="javascript:goList();">목록으로</button>
                <button type="submit" class="btn black mr5">글 수정</button>
            </div>
            </form>
            <form id="infoForm" method="get">
	            <input type="hidden" id="bno"         name="bno"          value="${before.bno}"/>
	            <input type="hidden" id="curPageNum"  name="curPageNum"   value="${pagingModel.curPageNum}"/>
	            <input type="hidden" id="viewPerPage" name="viewPerPage"  value="${pagingModel.viewPerPage}"/>
	            <input type="hidden" id="keyword"     name="keyword"      value="${pagingModel.keyword}">
	            <input type="hidden" id="type"        name="type"         value="${pagingModel.type}">
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
	
	const infoForm = $("#infoForm");
	
	/* 목록 페이지 이동*/
	function goList() {
		infoForm.find("#bno").remove();
		infoForm.attr("action", "/board/list");
		infoForm.submit();
	}
	
	/* 빈 값 체크 */
	function nullCheck() {
		
		let title = $("#title").val();
		let content = $("#content").val();
		
		if (title == ""){            
	        alert("제목을 입력해주세요.");
	        $("#title").focus();
	        return false;
	    }
		
		if (content == ""){            
	        alert("내용을 입력해주세요.");
	        $("#content").focus();
	        return false;
	    }
		return true;
	}
</script>
</body>
</html>