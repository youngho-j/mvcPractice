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
<script type="text/javascript">
// 로드 시 프로미스 실행
$(function() {
	getJSONData()
	.then(successAction)
	.catch(failAction);
});

$("#search").on("click", function(){
	getJSONData()
	.then(successAction)
	.catch(failAction);
});

function getJSONData() {
	
	let tableTitle = $('#tableTitle');
	let tableList = $('#tableList');
	let searchBar = $('input[name=keyword]');
	let select = $('select[name=selectOption]');
	
	let form = $('#searchForm').serialize();
	
	return new Promise((resolve, reject) => {
		$.ajax({
			  type : "POST",
			  url : "/crawling",
			  data : form,
			  success : function(result) {
				  let str = "";
				  
				  select.val(result['selectOption']).prop("selected", true);
				  
				  tableTitle.empty();
				  
				  tableTitle.html("<string>" + result['keyword'] + " " + $('select[name=selectOption] option:selected').text() + " 뉴스 </strong>");
				  
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
				  
				  resolve(result);
			  },
			  error : function(jqXHR) {
				  let str = "";
				  
				  alert("상태 : " + jqXHR.status + "\n원인 : " + jqXHR.responseText);
				  
				  str += "<tr>"
				  str += "<th scope='row' class='text-center'>"
				  str += jqXHR.responseText
				  str += "</th>"
				  str += "</tr>"
				  
				  tableList.html(str);
			  }
		});
	})
}

function successAction(data) {
	let downloadBtn = $('#excelDown');
	// 기존 등록된 이벤트 제거 
	// - 이벤트는 누적으로 등록되어 이벤트 삭제를 통해 다운로드가 두번 실행되는 것 방지
	downloadBtn.off("click");
	
	let result = JSON.stringify(data);
	
	downloadBtn.on("click", function(){
		$.ajax({
			  type : "POST",
			  url : "/downloadExcel",
			  contentType : 'application/json; charset=UTF-8',
			  data : result,
			  xhrFields:{
	                responseType: 'blob'
	           },
			  success : function(data, message, xhr) {
			        let fileName = "";
			        let disposition = xhr.getResponseHeader("Content-Disposition");

			        if (disposition && disposition.indexOf("attachment") !== -1) {
			            let filenameRegex = /filename[^;\n]*=((['"]).*?\2|[^;\n]*)/;
			            let matches = filenameRegex.exec(disposition);
			            if (matches != null && matches[1]) {
			                fileName = matches[1].replace(/['"]/g, '');
			            }
			        }
			        
			        let blob = new Blob([data]);
			        let link = document.createElement('a');
			        link.href = window.URL.createObjectURL(blob);
			        link.download = decodeURI(fileName);
			        link.click();
			  },
			  error : function(error, textStatus){
				  alert(textStatus);
			  }
		})
	});
}

function failAction() {
	alert(new Error("fail"));
}

</script>
</body>
</html>
