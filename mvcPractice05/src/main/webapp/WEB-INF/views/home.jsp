<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<body>
<table class="table table-hover">
	<thead class="thead-dark">
		<tr>
			<th scope="col" class="text-center" id="tableTitle"></th>
		</tr>
	</thead>
	<tbody id="tableList">
	</tbody>
</table>

<script type="text/javascript">
$(document).ready(function() {
	let tableTitle =  $('#tableTitle');
	let tableList =  $('#tableList');
	
	$.ajax({
		  type : "GET",
		  url : "/crawling",
		  success : function(result) {
			  let str = "";
			  
			  tableTitle.empty();
			  
			  tableTitle.html("<string>" + result['keyword'] +" 실시간 뉴스 </strong>");
			  
			  tableList.empty();
			  
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
});

</script>
</body>
</html>
