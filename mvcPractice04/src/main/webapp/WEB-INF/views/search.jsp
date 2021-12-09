<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wellcome MyShop</title>
<link rel="stylesheet" href="/resources/css/search.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous">
</script>
</head>
<body>
<div class="wrapper">
	<div class="wrap">
	
		<%@include file="../views/includes/user/topNavi.jsp" %>
		
		<div class="top_area">
			
			<%@include file="../views/includes/user/logo.jsp" %>
			
			<%@include file="../views/includes/user/searchBar.jsp" %>
			
			<%@include file="../views/includes/user/loginArea.jsp" %>
			
			<div class="clearfix"></div>
		</div>
		
		<%@include file="../views/includes/user/naviBar.jsp" %>
		
		<div class="content_area">
			<c:choose>
				<c:when test="${goodsListResult ne 'empty'}">
					<div class="goods_table_area">
						<table class="goods_table">
							<colgroup>
								<col width="110">
								<col width="*">
								<col width="120">
								<col width="120">
								<col width="120">
							</colgroup>
							<tbody>
								<c:forEach items="${goodsListResult}" var="list">
									<tr>
										<!-- 이미지, data 속성 대문자 입력 불가 -->
										<td class="image">
											<div class="image_area" 
												data-bookid="${list.imagesList[0].bookId}" 
												data-path="${list.imagesList[0].uploadPath}" 
												data-uuid="${list.imagesList[0].uuid}" 
												data-filename="${list.imagesList[0].fileName}">
												<img>
											</div>
										</td>
										<!-- 책 정보 -->
										<td class="book_info">
											<div class="category">
												[${list.categoryName}]
											</div>
											<div class="title">
												${list.bookName}
											</div>
											<div class="author">
												${list.authorName} 지음 | ${list.publisher} | ${list.publicationDate}
											</div>
										</td>
										<!--  -->
										<td class="rating_info">
											<div class="rating">
											</div>
										</td>
										<!-- 가격 -->
										<td class="price">
											<div class="origin_price">
												<del>
													<fmt:formatNumber value="${list.bookPrice}" pattern="#,### 원" />
												</del>
											</div>
											<div class="discount_price">
												<strong>
													<fmt:formatNumber value="${list.bookPrice * (1 - list.bookDiscount)}" pattern="#,### 원" />
												</strong>
											</div>
										</td>
										<td class="option"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<!-- 페이지 이동 인터페이스 영역 -->
	                <div class="pagingManger_area">
	                	<ul class="pagingManger_body">
		                	<!-- 이전 버튼 -->
		                	<c:if test="${pagingManager.prev}">
		                		<li class="pagingManager_btn prev">
		                    		<a href="${pagingManager.pageStartNum - 1}">이전</a>
		                    	</li>
		                    </c:if>
		                    		
		                	<!-- 페이지 번호 -->
		                	<c:forEach begin="${pagingManager.pageStartNum}" end="${pagingManager.pageEndNum}" var="num">
		                    	<li class="pagingManager_btn ${pagingManager.pageInfo.pageNum == num ? 'active':''}">
		                    		<a href="${num}">${num}</a>
		                    	</li>
		                    </c:forEach>
		                    		
		                    <!-- 다음 버튼 -->
		                    <c:if test="${pagingManager.next}">
		                    	<li class="pagingManger_btn next">
		                    		<a href="${pagingManager.pageEndNum + 1}">다음</a>
		                    	</li>
		                    </c:if>
	                   	</ul>
	                </div>
					
					<!-- 페이지 이동 form -->
	            	<form id="moveForm" action="/search" method="get">
						<input type="hidden" name="pageNum" value="${pagingManager.pageInfo.pageNum}">
						<input type="hidden" name="viewPerPage" value="${pagingManager.pageInfo.viewPerPage}">
						<input type="hidden" name="keyword" value="${pagingManager.pageInfo.keyword}">
						<input type="hidden" name="type" value="${pagingManager.pageInfo.type}">
						<input type="hidden" name="categoryCode" value="${pagingManager.pageInfo.categoryCode}">
					</form>
					
				</c:when>
				<c:otherwise>
					<div class="empty_table">
	                	검색 결과가 존재하지 않습니다.
	                </div>
				</c:otherwise>			
			</c:choose>
		</div>
        
        <%@include file="../views/includes/user/footer.jsp" %>
        
	</div>
</div>
<script type="text/javascript">
	
	$(document).ready(function(){
		let pickedType = '<c:out value="${pagingManager.pageInfo.type}"/>';
		
		/* 검색 타입 속성 부여 */
		if(pickedType != "") {
			$("select[name='type']").val(pickedType).attr("selected", "selected");
		}
		
		/* 이미지 호출 */
		$(".image_area").each(function(i, obj){
			let imageArea = $(obj);
			
			if(imageArea.data("bookid")) {
				
				let uploadPath = imageArea.data("path").substr(23);
				let uuid = imageArea.data("uuid");
				let fileName = imageArea.data("filename");
				
				let imageFile = 
					encodeURIComponent(uploadPath + "\\t_" + uuid + "_" + fileName);
				
				$(this).find("img").attr('src', '/display?fileName=' + imageFile);
			
			} else {
			
				$(this).find("img").attr('src', '/resources/img/NoImage.png');
			}
			
		});
	});


	/* 로그아웃 */
	$("#top_navi_logout_btn").click(function(){
		$.ajax({
			type:"POST",
			url:"/member/logout",
			success:function(data){
				document.location.reload();
			}
		});
	});
	
	let moveForm = $("#moveForm");
	
	/* 페이지 이동 */
	$(".pagingManager_btn a").on("click", function(e){
	
		e.preventDefault();
		
		moveForm.find("input[name='pageNum']").val($(this).attr("href"));
		
		moveForm.submit();
	
	});
	
</script>
</body>
</html>