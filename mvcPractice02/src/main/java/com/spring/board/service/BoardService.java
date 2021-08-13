package com.spring.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.board.common.CommonDto;
import com.spring.board.common.CommonForm;
import com.spring.board.common.PagingUtil;
import com.spring.board.common.ResultUtil;
import com.spring.board.dao.BoardDao;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardFileForm;
import com.spring.board.form.BoardForm;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	// 게시글 목록 조회
	public ResultUtil getBoardList(BoardForm boardForm) throws Exception {
		
		ResultUtil resultUtil = new ResultUtil();
		
		CommonDto commonDto = new CommonDto();
		
		int totalCount = boardDao.getBoardCnt(boardForm);
		
		if(totalCount != 0) {
			CommonForm commonForm = new CommonForm();
			
			commonForm.setFuntion_name(boardForm.getFuntion_name());
			commonForm.setCurrent_page_num(boardForm.getCurrent_page_num());
			commonForm.setCount_per_page(10);
			commonForm.setCount_per_list(10);
			commonForm.setTotal_list_count(totalCount);
			
			// 페이징 처리 정보를 담은 객체
			commonDto = PagingUtil.setPageUtil(commonForm);
		}
		
		boardForm.setLimit(commonDto.getLimit());
		boardForm.setOffset(commonDto.getOffset());
		
		List<BoardDto> list = boardDao.getBoardList(boardForm);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("list", list);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pagination", commonDto.getPagination());
		
		resultUtil.setData(resultMap);
		resultUtil.setState("SUCCESS");
		
		return resultUtil;
	}
	
	// 게시글 상세 정보 조회
	public BoardDto getBoardDetail(BoardForm boardForm) throws Exception {
		
		BoardDto boardDto = new BoardDto();
		
		String searchType = boardForm.getSearch_type();
		
		if("S".equals(searchType)) {
			int updateHitCnt = boardDao.updateBoardHits(boardForm);
			
			if(updateHitCnt > 0) {
				boardDto = boardDao.getBoardDetail(boardForm);
			}
			
		} else {
			boardDto = boardDao.getBoardDetail(boardForm);
		}
		
		// 다운로드를 위해 첨부파일 정보 조회
		BoardFileForm boardFileForm = new BoardFileForm();
		boardFileForm.setBoard_seq(boardForm.getBoard_seq());
		
		boardDto.setFiles(boardDao.getBoardFileList(boardFileForm));
		
		return boardDto;
	}
	
	// 게시글 작성
	public BoardDto insertBoard(BoardForm boardForm) throws Exception {
		
		int insertCnt = 0;
		
		BoardDto boardDto = new BoardDto();
		
		// 그룹 번호 조회
		int boardReRef = boardDao.getBoardReRef(boardForm);
		
		boardForm.setBoard_re_ref(boardReRef);
		
		insertCnt = boardDao.insertBoard(boardForm);
		
		// 파일 등록
		List<BoardFileForm> boardFileList = getBoardFileInfo(boardForm);
		
		for(BoardFileForm boardFileForm : boardFileList) {
			boardDao.insertBoardFile(boardFileForm);
		}
		
		if(insertCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");			
		}
		
		return boardDto;
	}
	
	// 게시글 수정
	public BoardDto updateBoard(BoardForm boardForm) throws Exception {
		
		BoardDto boardDto = new BoardDto();
		
		int updateCnt = boardDao.updateBoard(boardForm);
		
		// 첨부된 파일 삭제 
		String deleteFile = boardForm.getDelete_file();
		
		if(!"".equals(deleteFile)) {
			
			String[] deleteFileInfo = deleteFile.split("!");
			
			int boardSeq = Integer.parseInt(deleteFileInfo[0]);
			int fileNum = Integer.parseInt(deleteFileInfo[1]);
			
			BoardFileForm deleteBoardFileForm = new BoardFileForm();
			
			deleteBoardFileForm.setBoard_seq(boardSeq);
			deleteBoardFileForm.setFile_num(fileNum);
			
			boardDao.deleteBoardFile(deleteBoardFileForm);
		}
		
		// 첨부 파일 등록
		List<BoardFileForm> boardFileList = getBoardFileInfo(boardForm);
				
		for(BoardFileForm boardFileForm : boardFileList) {
			boardDao.insertBoardFile(boardFileForm);
		}
		
		if(updateCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");			
		}
		
		return boardDto;
	}
	
	// 게시글 삭제
	public BoardDto deleteBoard(BoardForm boardForm) throws Exception {
		BoardDto boardDto = new BoardDto();
		
		int deleteCnt = boardDao.deleteBoard(boardForm);
		
		if(deleteCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");
		}
		return boardDto;
	}
	
	// 답글 등록
	public BoardDto insertBoardReply(BoardForm boardForm) throws Exception {
		int insertCnt = 0;
		
		BoardDto boardDto = new BoardDto();
		
		// 답글 작성을 위한 부모 게시글 정보 조회
		BoardDto boardReplyInfo = boardDao.getBoardReplyInfo(boardForm);
		
		boardForm.setBoard_seq(boardReplyInfo.getBoard_seq());
		boardForm.setBoard_re_ref(boardReplyInfo.getBoard_re_ref());
		boardForm.setBoard_re_lev(boardReplyInfo.getBoard_re_lev());
		boardForm.setBoard_re_seq(boardReplyInfo.getBoard_re_seq());
		
		// 기존 작성된 답글의 번호 증가
		insertCnt += boardDao.updateBoardReSeq(boardForm);
		
		// 답글 작성
		insertCnt += boardDao.insertBoardReply(boardForm);
		
		if(insertCnt > 0) {
			boardDto.setResult("SUCCESS");
		} else {
			boardDto.setResult("FAIL");			
		}
		
		return boardDto;
	}
	
	// 첨부 파일 정보 조회
	public List<BoardFileForm> getBoardFileInfo(BoardForm boardForm) throws Exception {
		BoardFileForm boardFileForm = new BoardFileForm();
		
		List<BoardFileForm> boardFileList = new ArrayList<BoardFileForm>();
		
		List<MultipartFile> files = boardForm.getFiles();
		
		int boardSeq = boardForm.getBoard_seq();
        String fileName = null;
        String fileExt = null;
        String fileNameKey = null;
        String fileSize = null;
        
        // 파일이 저장될 디렉토리 경로 설정
        String filePath = "H:\\RebuildProject2file";
        
        if(files != null && files.size() > 0) {
        	
        	File file = new File(filePath);
        	
        	if(!file.exists()) {
        		file.mkdirs();
        	}
        	
        	for(MultipartFile multipartFile : files) {
        		fileName = multipartFile.getOriginalFilename();
        		fileExt = fileName.substring(fileName.lastIndexOf("."));
        		
        		// 파일명을 UUID로 암호화하여 저장
        		fileNameKey = getRandomString() + fileExt;
        		
        		fileSize = String.valueOf(multipartFile.getSize());
        		
        		file = new File(filePath + "/" + fileNameKey);
        		
        		multipartFile.transferTo(file);

                boardFileForm = new BoardFileForm();
                boardFileForm.setBoard_seq(boardSeq);
                boardFileForm.setFile_name(fileName);
                boardFileForm.setFile_name_key(fileNameKey);
                boardFileForm.setFile_path(filePath);
                boardFileForm.setFile_size(fileSize);
                boardFileList.add(boardFileForm);
        	}
        }
		return boardFileList;
	}
	
	// 랜덤문자열 생성
	public String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
