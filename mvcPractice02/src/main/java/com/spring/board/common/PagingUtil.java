package com.spring.board.common;


// 페이지 네비게이션 정보 설정 클래스
public class PagingUtil {
	
	public static CommonDto setPageUtil(CommonForm commonForm) {
		CommonDto commonDto = new CommonDto();
		
		// 페이징 결과값
		String pagination = "";
		
		// 자바스크립트 함수 명
		String functionName = commonForm.getFuntion_name();
		
		// 현재 페이지 번호
		int currentPage = commonForm.getCurrent_page_num();
		
		// 한 화면에 출력될 게시물 수
		int countPerList = commonForm.getCount_per_list();
		
		// 한 화면에 출력될 페이지 수
		int countPerPage = commonForm.getCount_per_page();
		
		// 총 게시물 수
		int totalListCount = commonForm.getTotal_list_count();
		
		// 총 페이지 수, int 형식으로 표현되어 나머지가 있을 경우 아래 조건으로 +1 하여 페이지 수 수정
		int totalPageCount = totalListCount / countPerList;
		if(totalListCount % countPerList > 0) {
			totalPageCount++;
		}
		
		// 화면의 첫 페이지 번호
		int viewFirstPage = (((currentPage - 1) / countPerPage) * countPerPage) + 1; 
        
		// 화면의 마지막 페이지 번호, 마지막 페이지가 전체 페이지의 수 보다 큰 경우 게시물이 존재하지않으므로 마지막 페이지 수를 수정
		int ViewLastPage = viewFirstPage + countPerPage - 1; 
        if (ViewLastPage > totalPageCount) { 
            ViewLastPage = totalPageCount;
        }
        
        // 전체 페이지 중 첫 페이지
        int totalFirstPage = 1; 
        
        // 전체 페이지 중 마지막 페이지
        int totalLastPage = totalPageCount; 
        
        // 현재 페이지의 이전 번호
        int prePerPage = 0; 
        if (viewFirstPage - countPerPage > 0) {
            prePerPage = viewFirstPage - countPerPage;
        } else {
            prePerPage = totalFirstPage;
        }
        
        //현재 페이지의 다음 번호
        int nextPerPage = 0; 
        if (viewFirstPage + countPerPage < totalPageCount) {
            nextPerPage = viewFirstPage + countPerPage;
        } else {
            nextPerPage = totalPageCount;
        }
        
        // 페이지 네비게이션 결과값
        pagination += "<div class='pagination'>";
        pagination += "<a href='javascript:" + functionName + "(\"" + totalFirstPage + "\");' class=\"direction_left01\">[<<]</a>";
        pagination += "<a href='javascript:" + functionName + "(" + prePerPage + ");' class=\"direction_left01\">[<]</a>";
        
        for (int a = viewFirstPage; a <= ViewLastPage; a++) {
            if (a == currentPage) {
                pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");' class='onpage'>[" + a + "]</a>";
            } else {
                pagination += "<a href='javascript:" + functionName + "(\"" + a + "\");'>[" + a + "]</a>";
            }
        }
        
        pagination += "<a href='javascript:" + functionName + "(" + nextPerPage + ");' class=\"direction_right01\">[>]</a>";
        pagination += "<a href='javascript:" + functionName + "(" + totalLastPage + ");' class=\"direction_right01\">[>>]</a>";
        pagination += "</div>";
        
        // 화면에 보여지는 게시물의 시작 번호
        int offset = ((currentPage - 1) * countPerList); 
 
        // LIMIT는 가져올 row의 수, OFFSET은 몇 번째 row부터 가져올지를 결정
        commonDto.setLimit(countPerList);
        commonDto.setOffset(offset);
        commonDto.setPagination(pagination);
 
        return commonDto;
	}
}
