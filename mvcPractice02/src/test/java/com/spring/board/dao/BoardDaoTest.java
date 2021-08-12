package com.spring.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardFileDto;
import com.spring.board.form.BoardFileForm;
import com.spring.board.form.BoardForm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml","file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Transactional
public class BoardDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);
	
	@Inject
	private BoardDao boardDao;
	
	private BoardForm form;
	private BoardFileForm fileForm;
	
	@Before
	public void setUp() throws Exception {
		form = new BoardForm();
		fileForm = new BoardFileForm();
	}
	
	@After
	public void init() throws Exception {
		form = new BoardForm();
		fileForm = new BoardFileForm();
	}
	
	@Test
	public void List_BoardDto_리턴() throws Exception {
		form.setLimit(3);
		form.setOffset(0);
		
		List<BoardDto> list = boardDao.getBoardList(form);
		
		logger.info("List 조회");
		logger.info("===================================");
		
		if(list.size() > 0) {
			for(BoardDto dto : list) {
				logger.info("번호 : " + dto.getBoard_seq());
				logger.info("제목 : " + dto.getBoard_subject());
				logger.info("조회수 : " + dto.getBoard_hits());
				logger.info("==================================");
			}
			BoardDto dto1 = list.get(0);
			assertThat(dto1.getBoard_content()).isEqualTo("게시글 내용1");
		} else {
			logger.info("데이터 없음");
		}
	}
	
	@Test
	public void 상세_BoardDto_리턴() throws Exception {
		form.setBoard_seq(1);
		
		BoardDto dto = boardDao.getBoardDetail(form);
		
		logger.info("Dto 조회");
		logger.info("===================================");
		
		if(!dto.equals(null)) {
			logger.info("번호 : " + dto.getBoard_seq());
			logger.info("제목 : " + dto.getBoard_subject());
			logger.info("조회수 : " + dto.getBoard_hits());
			logger.info("==================================");
			assertThat(dto.getBoard_content()).isEqualTo("게시글 내용1");
		} else {
			logger.info("데이터 없음");
		}
	}
	
	@Test
	@Rollback(true)
	public void 조회수_증가_테스트() throws Exception {
		form.setBoard_seq(1);
		
		logger.info("조회수 업데이트");
		logger.info("===================================");
		
		int hits = boardDao.updateBoardHits(form);
		
		if(hits == 1) {
			logger.info("조회수 업데이트 성공 " + hits);
			assertThat(hits).isEqualTo(1);
		} else {
			logger.info("조회수 업데이트 실패");			
		}
	}
	
	@Test
	@Rollback(true)
	public void 게시글_등록_테스트() throws Exception {
		form.setBoard_writer("테스트1");
		form.setBoard_subject("테스트");
		form.setBoard_content("테스트입니다");
		
		int result = boardDao.insertBoard(form);
		
		if(result == 1) {
			logger.info("게시글 등록 성공 " + result);
		} else {
			logger.info("게시글 등록 실패");			
		}
	}
	
	@Test
	@Rollback(true)
	public void 게시글_수정_테스트() throws Exception {
		form.setBoard_subject("수정테스트");
		form.setBoard_content("수정된애용입니다");
		form.setBoard_seq(3);
		
		int result = boardDao.updateBoard(form);
		
		if(result == 1) {
			logger.info("게시글 수정 성공 " + result);
		} else {
			logger.info("게시글 수정 실패");			
		}
	}
	
	@Test
	@Rollback(true)
	public void 게시글_삭제_테스트() throws Exception {
		form.setBoard_seq(3);
		
		int result = boardDao.deleteBoard(form);
		
		if(result == 1) {
			logger.info("게시글 삭제 성공");
		} else {
			logger.info("게시글 삭제 실패");			
		}
	}
	
	@Test
	public void 전체_게시글_갯수출력_테스트() throws Exception {
		int result = boardDao.getBoardCnt(form);
		
		assertThat(result).isEqualTo(5);
	}
	
	@Test
	public void 게시글_그룹번호_조회_테스트() throws Exception {
		assertThat(boardDao.getBoardReRef(form)).isEqualTo(1);
	}
	
	@Test
	public void 답글_정보_조회_테스트() throws Exception {
		form.setBoard_parent_seq(1);
		BoardDto boardDto = boardDao.getBoardReplyInfo(form);
		assertThat(boardDto.getBoard_re_ref()).isEqualTo(0);
	}
	
	@Test
	@Rollback(true)
	public void 답글_순서_수정_테스트() throws Exception {
		form.setBoard_re_ref(0);
		form.setBoard_re_seq(0);
		int result = boardDao.updateBoardReSeq(form);
		if(result == 1) {
			logger.info("기존 답글 번호 증가 성공");
		} else {
			logger.info("실패!");			
		}
	}
	
	@Test
	@Rollback(true)
	public void 답글_등록하기_테스트() throws Exception {
		form.setBoard_re_ref(1);
		form.setBoard_re_lev(0);
		form.setBoard_re_seq(0);
		form.setBoard_writer("답글테스트1");
		form.setBoard_subject("답글테스트");
		form.setBoard_content("답글테스트입니다");
		
		int result = boardDao.insertBoardReply(form);
		
		if(result == 1) {
			logger.info("답글 등록 성공");
		} else {
			logger.info("실패!");			
		}
	}
	
	@Test
	@Rollback(true)
	public void 파일_등록하기_테스트() throws Exception {
		fileForm.setBoard_seq(35);
		fileForm.setFile_name_key("file");
		fileForm.setFile_name("사진1.jpg");
		fileForm.setFile_path("C:");
		fileForm.setFile_size("324KB");
		
		int result = boardDao.insertBoardFile(fileForm);
		
		if(result == 1) {
			logger.info("파일 등록 성공");
		} else {
			logger.info("실패!");			
		}
	}
	
	@Test
	public void 게시글_첨부파일_정보조회_테스트() throws Exception {
		fileForm.setBoard_seq(54);
		List<BoardFileDto> list = boardDao.getBoardFileList(fileForm);
		
		assertThat(list.size()).isEqualTo(1);
	}
	
	@Test
	@Rollback(true)
	public void 게시글_첨부파일_삭제_상태변경_테스트() throws Exception {
		fileForm.setBoard_seq(54);
		fileForm.setFile_num(1);
		
		int result = boardDao.deleteBoardFile(fileForm);
		
		if(result == 1) {
			logger.info("첨부파일 상태 변경 성공");
		} else {
			logger.info("실패!");			
		}
	}
}
