<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link 
	rel="stylesheet" 
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" 
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" 
	crossorigin="anonymous">
<script
	src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
	integrity="sha512-5WvZa4N7Jq3TVNCp4rjcBMlc6pT3lZ7gVxjtI6IkKW+uItSa+rFgtFljvZnCxQGj8SUX5DHraKE6Mn/4smK1Cg==" 
	crossorigin="anonymous" 
	referrerpolicy="no-referrer">
</script>
</head>
</head>
<body class="pt-5">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container-fluid">
		<b class="navbar-brand">Hello News</b>
	</div>
</nav>
<div class="container-fluid">
	<div class="text-center">
		<div class="bg-image row align-items-end" 
			style="background-image: url('https://img.freepik.com/free-vector/news-concept-for-landing-page_52683-20522.jpg?w=1380');
				background-size: 100% 100%;
				height: 300px;
				width: 100;	">
			<div class="col">
			</div>
			<div class="col-4 mb-2" >
				<form class="form-inline" id="searchForm">
					<input name="keyword" class="form-control col-lg" placeholder="검색어 입력">
					<button id="search" type="button" class="btn btn-primary">검색</button>
				</form>
			</div>
			<div class="col">
			</div>
		
		</div>
	</div>
</div>
<div class="container-fluid">
	<table class="table table-hover">
		<thead class="thead-dark">
			<tr>
				<th scope="col" class="text-center" id="tableTitle"></th>
			</tr>
		</thead>
		<tbody id="tableList">
		</tbody>
	</table>
</div>
<script type="text/javascript">
// DOCUMENT READY 사용 이유
// .ready() -> DOM(Document Object Model)이 완전히 불러와지면 실행되는 이벤트 
// 즉, 디자인이 적용되지 않은 문서 구조가 만들어진 시점에 실행되며 Event가 계속 발생
// .ready() Event는 1.8 버전에서 deprecated 됨 대신, $()을 사용
// (참고, DOMContentLoaded 좀 더 알아보기 - ready()와 비슷한 기능)
$(function() {
	getJSONData();
	
	$("#search").click(getJSONData);
});
function getJSONData () {
	let tableTitle =  $('#tableTitle');
	let tableList =  $('#tableList');
	let searchBar = $('input[name=keyword]');
	let form = $('#searchForm').serialize();
	$.ajax({
		  type : "POST",
		  url : "/crawling2",
		  data : form,
		  success : function(result) {
			  let str = "";
			  
			  tableTitle.empty();
			  
			  tableTitle.html("<string>" + result['keyword'] +" 실시간 뉴스 </strong>");
			  
			  tableList.empty();
			  
			  searchBar.val(result['keyword']);
			  
			  let list = result['newsList'];
			  
			  $.each(list, function(i){
			
				  str += "<tr>"
				  str += "<th scope='row' class='text-center'>"
				  str += "<a href="+ list[i].newsURL + " target='_blank'>" + list[i].newsTitle + "</a>"
				  str += "</th>"
				  str += "</tr>"
				  
			  });
			  
			  tableList.html(str);
		  },
		  error : function(error, status, msg) {
			  let str = "";
			  
			  alert("상태 코드 : " + status + "\n" + "원인 : " + msg);
			  
			  str += "<tr>"
			  str += "<th scope='row' class='text-center'>"
			  str += msg
			  str += "</th>"
			  str += "</tr>"
			  
			  tableList.html(str);
		  }
	});
}
</script>
</body>
</html>
