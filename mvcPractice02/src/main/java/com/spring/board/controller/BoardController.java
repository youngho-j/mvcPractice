package com.spring.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.common.ResultUtil;
import com.spring.board.dto.BoardDto;
import com.spring.board.form.BoardForm;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 게시판 - 목록 페이지 이동 */
	@RequestMapping(value = "/boardList")
	public String boardList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "board/boardList";
	}
	
	/* 게시판 - 목록 데이터 조회 */
	@RequestMapping(value = "/getBoardList")
	@ResponseBody
	public ResultUtil getBoardList(HttpServletRequest req, HttpServletResponse res, BoardForm boardform) throws Exception {
		ResultUtil resultUtil = boardService.getBoardList(boardform);
		return resultUtil;
	}
	
	/* 게시판 - 상세 페이지 이동 */
	@RequestMapping(value = "/boardDetail")
	public String boardDetail(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "board/boardDetail";
	}
	
	/* 게시판 - 상세 데이터 조회 */
	@RequestMapping(value = "/getBoardDetail")
	@ResponseBody
	public BoardDto getBoardDetail(HttpServletRequest req, HttpServletResponse res, BoardForm boardForm) throws Exception {
		MDC.put("ID", String.valueOf(boardForm.getBoard_seq()));
		
		BoardDto boardDto = boardService.getBoardDetail(boardForm);
		
		MDC.remove("ID");
		
		return boardDto;
	}
	
	/* 게시판 - 등록 페이지 이동 */
	@RequestMapping(value = "/boardWrite")
	public String boardWrite(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "board/boardWrite";
	}
	
	/* 게시판 - 등록 */
	@RequestMapping(value = "/insertBoard")
	@ResponseBody
	public BoardDto insertBoard(HttpServletRequest req, HttpServletResponse res, BoardForm boardForm) throws Exception {
		BoardDto boardDto = boardService.insertBoard(boardForm);
		return boardDto;
	}
	
	/* 게시판 - 수정 페이지 이동 */
	@RequestMapping(value = "/boardUpdate")
	public String boardUpdate(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "board/boardUpdate";
	}
	
	/* 게시판 - 수정 */
	@RequestMapping(value = "/updateBoard")
	@ResponseBody
	public BoardDto updateBoard(HttpServletRequest req, HttpServletResponse res, BoardForm boardForm) throws Exception {
		BoardDto boardDto = boardService.updateBoard(boardForm);
		return boardDto;
	}
	
	/* 게시판 - 삭제 */
	@RequestMapping(value = "/deleteBoard")
    @ResponseBody
    public BoardDto deleteBoard(HttpServletRequest req, HttpServletResponse res, BoardForm boardForm) throws Exception{
        BoardDto boardDto = boardService.deleteBoard(boardForm);
        return boardDto;
    }
	
	/* 게시판 - 답글 입력 페이지 이동 */
	@RequestMapping(value = "/boardReply")
	public String boardReply(HttpServletRequest req ,HttpServletResponse res) throws Exception {
		return "board/boardReply";
	}
	
	/* 게시판 - 답글 등록*/
	@RequestMapping(value = "/insertBoardReply")
	@ResponseBody
	public BoardDto insertBoardReply(HttpServletRequest req ,HttpServletResponse res, BoardForm boardForm) throws Exception {
		BoardDto boardDto = boardService.insertBoardReply(boardForm);
		return boardDto;
	}
	
	/*게시판 - 첨부 파일 다운로드 */
	@RequestMapping("/fileDownload")
	public ModelAndView fileDownload(@RequestParam("fileNameKey") String fileNameKey
									,@RequestParam("fileName") String fileName
									,@RequestParam("filePath") String filePath ) throws Exception {
		
		Map<String, Object> fileInfo = new HashMap<String, Object>();
		fileInfo.put("fileNameKey", fileNameKey);
		fileInfo.put("fileName", fileName);
		fileInfo.put("filePath", filePath);
		
		return new ModelAndView("fileDownloadUtil", "fileInfo", fileInfo);
	}
}
