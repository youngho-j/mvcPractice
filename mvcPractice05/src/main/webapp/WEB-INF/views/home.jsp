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
<script defer src="/resources/javaScript/homeScript.js"></script>
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
				<form class="form-inline" id="searchForm" onsubmit="return false">
					<select name="selectOption" class="custom-select">
					  <option value="1" selected="selected">최신</option>
					  <option value="0">관련</option>
					</select>
					<input name="keyword" class="form-control col-lg" placeholder="검색어 입력">
					<button id="search" type="button" class="btn btn-primary">검색</button>
				</form>
			</div>
			<div class="col mb-2">
				<button id="excelDown" type="button" class="btn btn-primary">엑셀 다운로드</button>
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
</body>
</html>
