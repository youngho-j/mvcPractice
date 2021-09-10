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
                <c:choose>
	                <c:when test="${not empty list}">
		                <c:forEach items="${list}" var="list">
					    	<tr>
					        	<td><c:out value="${list.bno}"/></td>
					            <td onclick='javascript:goDetail("<c:out value="${list.bno}"/>");' style='cursor:Pointer'><c:out value="${list.title}"/></td>
					            <td><c:out value="${list.writer}"/></td>
					            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${list.regdate}" /></td>
					            <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${list.updateDate}" /></td>
					        </tr>
					    </c:forEach>
	                </c:when>
	                <c:otherwise>
	                	<tr>
		            		<td colspan='5'>해당 키워드로 등록된 글이 존재하지 않습니다.</td>
		            	</tr>
	                </c:otherwise>
			    </c:choose>   
            </table>
            <div id="search">
 				<div class="btn_right mt15">
 					<form id="searchForm">
 					<select name=type class="selbox">
 						<option value=""    <c:out value="${pageData.pagingModel.type == null?'selected':''}"/>>---</option>
 						<option value="T"   <c:out value="${pageData.pagingModel.type == 'T'?'selected':''}"/>>제목</option>
 						<option value="C"   <c:out value="${pageData.pagingModel.type == 'C'?'selected':''}"/>>내용</option>
 						<option value="W"   <c:out value="${pageData.pagingModel.type == 'W'?'selected':''}"/>>작성자</option>
 						<option value="TC"  <c:out value="${pageData.pagingModel.type == 'TC'?'selected':''}"/>>제목 + 내용</option>
 						<option value="TW"  <c:out value="${pageData.pagingModel.type == 'TW'?'selected':''}"/>>제목 + 작성자</option>
 						<option value="TCW" <c:out value="${pageData.pagingModel.type == 'TCW'?'selected':''}"/>>제목 + 내용 + 작성자</option>
 					</select>
 					<input type="text" name="keyword" value="${pageData.pagingModel.keyword}" class="tbox01 mr5">
 					<button type="submit" id="search_button" class="btn black mr5">Search</button>
 					</form>
 				</div>           
            </div>
			<div id="paging">
				<ul class="pagination">
					<!-- 이전 페이지 -->
					<c:if test="${pageData.prev}">
						<li class="paginate_prev_button"><a href="${pageData.pagingModel.curPageNum - 1}" class="direction_left01">[Prev]</a></li>						
					</c:if>
					<c:forEach var="num" begin="${pageData.startPage}" end="${pageData.endPage}">
						<li class="paginate_button ${pageData.pagingModel.curPageNum == num ? 'active':'' }"><a href="${num}" class="onpage">${num}</a></li>
					</c:forEach>
					<!-- 다음 페이지 -->
					<c:if test="${pageData.next}">
						<li class="paginate_next_button"><a href="${pageData.pagingModel.curPageNum + 1}" class="direction_right01">[Next]</a></li>						
					</c:if>
				</ul>
			</div>
            <div class="btn_right mt15">
                <button type="button" class="btn black mr5" onclick="javascript:goEnroll();">게시글 작성</button>
            </div>
		</div>
        <form id="moveForm" method="get">
         	<input type="hidden" name="curPageNum"  value="${pageData.pagingModel.curPageNum}">	
          	<input type="hidden" name="viewPerPage" value="${pageData.pagingModel.viewPerPage}">
          	<input type="hidden" name="keyword"     value="${pageData.pagingModel.keyword}">
          	<input type="hidden" name="type"        value="${pageData.pagingModel.type}">
        </form>
	</div>
</div>
<%--
기존 스크립트 사용시 html 파싱 도중 스크립트 실행으로 인해 preventDefault 사용 불가하여
hmtl 파싱 후 script를 실행할 수 있도록 변경 단, 웹이 자바스크립트에 의존적일 경우 단점으로 작용할 수 있음
 --%>
<script type="text/javascript">
	/* 리스트 페이지 출력시 값을 result 값을 통해 등록, 수정, 삭제 등 결과 출력 */
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
            alert("성공!");
        }
	}
	
	/* 글 등록 페이지 이동 */
	function goEnroll() {         
        location.href = "/board/enroll";
    }
	
	var moveForm = $("#moveForm");
	
	/* 페이지 이동 번호 클릭시 해당 페이지로 이동 */
	$(".pagination a").click(function(e){
		/* a 태그 클릭시 실행을 막는 메서드 */
		e.preventDefault();
		/* moveForm의 하위 값 중 "input[name='curPageNum']"를 찾아 a 태그의 href 속성 값을 넣어줌 */
		moveForm.find("input[name='curPageNum']").val($(this).attr("href"));
		moveForm.attr("action", "/board/list");
		moveForm.submit();
	});
	
	/* 글 상세 페이지 이동 */
	function goDetail(bno) {
		moveForm.append("<input type='hidden' name='bno' value='" + bno + "'>'");
		moveForm.attr("action", "/board/detail");
		moveForm.submit();
	}
	
	function typeCheck(type) {
		if(!type) {
			alert("검색 타입을 선택하세요.");
			return false;
		}
	}
	
	function keywordCheck(keyword) {
		if(!keyword) {
			alert("키워드를 입력하세요.");
			return false;
		}
	}
	
	
	const searchForm = document.querySelector('#searchForm');
	
	/* 검색시 엔터 or 클릭 모두 사용 가능 */
	searchForm.addEventListener('submit', function(e){
		e.preventDefault();
		
		let keyword = $("input[name = 'keyword']").val();
		let type = $("select[name = 'type']").val();
		
		if(!type) {
			alert("검색 타입을 선택하세요.");
			return false;
		}
		
		if(!keyword) {
			alert("키워드를 입력하세요.");
			return false;
		}
		
		moveForm.find("input[name='type']").val(type);
		moveForm.find("input[name='keyword']").val(keyword);
		moveForm.find("input[name='curPageNum']").val(1);
		/*action 속성을 따로 지정하지 않아도 현재 url 경로의 매핑된 메서드를 호출하기때문에 적어주지 않아도됨*/
		moveForm.attr("action", "/board/list");
		moveForm.submit();
	});
	
</script>
</body>
</html>