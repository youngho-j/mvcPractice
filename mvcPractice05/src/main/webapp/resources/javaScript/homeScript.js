/*
	함수 목록
	getJSONData() 
	- Promise 함수로 생성
	- 크롤링 해온 뉴스 데이터를 출력
	
	successAction()
	받아온 크롤링 데이터를 xlsx 형태로 변환하여 다운로드 진행
	
	failAction()
	에러 발생시 alert 창 출력
*/
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
				
				reject(jqXHR.status);	
				
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
			  success : function(data, _message, xhr) {
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
				  alert("Error : " + error + "textStatus : " + textStatus);
			  }
		})
	});
}

function failAction(reason) {
	alert(new Error("ErrorCode : " + reason));
}