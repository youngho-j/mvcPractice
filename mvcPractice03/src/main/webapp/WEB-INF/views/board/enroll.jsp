<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 등록 페이지</title>
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
			<h1>게시글 등록 페이지</h1>
			<form action="/board/enroll" method="post" onsubmit="return nullCheck()">
				<table width="100%" class="table02">
				<caption>
					<strong>
					<span class="t_red">*</span> 표시는 필수입력 항목입니다.
					</strong>
				</caption>
			        <colgroup>
			        	<col width="20%">
			        	<col width="*">
			        </colgroup>
					<tbody id="tbody">
						<tr>
					        <th>제목<span class="t_red">*</span></th>
					        <td><input id="title" name="title" value="" class="tbox01"></td>
						</tr>
						<tr>
					        <th>내용<span class="t_red">*</span></th>
					        <td><input id="content" name="content" value="" class="tbox01"></td>
						</tr>
						<tr>
					        <th>작성자<span class="t_red">*</span></th>
					        <td><textarea id="writer" name="writer" cols="10" rows="5" class="textarea01"></textarea></td>
						</tr>
					</tbody>
				</table>
				<div class="btn_right mt15">
					<button type="button" class="btn black" onclick="javascript:goList();">목록으로</button>						
					<button type="submit" class="btn black mr5">글 등록</button>						
				</div>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
	/* 목록으로 이동 */
	function goList() {
		location.href = "/board/list";
	}
	
	/* 빈 값 체크 */
	function nullCheck() {
		
		let title = $("#title").val();
		let content = $("#content").val();
		let writer = $("#writer").val();
		
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
		
		if (writer == ""){            
            alert("작성자를 입력해주세요.");
            $("#writer").focus();
            return false;
        }
	}
	
</script>
</body>
</html>