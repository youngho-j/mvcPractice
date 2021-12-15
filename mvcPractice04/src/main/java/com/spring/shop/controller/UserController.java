package com.spring.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.shop.service.BookService;
import com.spring.shop.service.MemberService;
import com.spring.shop.util.MailManager;
import com.spring.shop.util.PageInfo;
import com.spring.shop.util.PagingManager;
import com.spring.shop.vo.BookVO;
import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private MailManager mailManager;
	
	// 메인 페이지 이동
	@GetMapping({"/main", "/"})
	public String mainPageGET(Model model) throws Exception {
		log.info("메인 페이지 진입");
			
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", bookService.getDomesticCategoryCode());
		model.addAttribute("international", bookService.getInternationalCategoryCode());
			
		return "user/main";
	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/goLogin", method = RequestMethod.GET)
	public String loginGET() throws Exception {
		log.info("로그인 페이지 진입");
		return "user/goLogin";
	}
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinGET() throws Exception {
		log.info("회원가입 페이지 진입");
		return "user/join";
	}
	
	// 회원가입 후 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO memberVO) throws Exception {
		
		memberService.memberJoin(memberVO);
		
		log.info("회원가입 성공");
		
		return "redirect:/main";
	}
	
	// 아이디 중복검사
	@RequestMapping(value = "/memberIdChk", method = RequestMethod.POST)
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {
		log.info("아이디 체크");
		
		int result = memberService.idCheck(memberId);
		
		if(result != 0) {
			log.info("중복 아이디 O");
			return "fail";
		}
		log.info("중복 아이디 X");
		return "success";
	}
	
	// 인증메일
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		
		log.info("이메일 데이터 전송 확인 : " + email);
		
		mailManager = new MailManager(mailSender);
		
		String check = mailManager.sendAuthEmail(email);
		
		return check;
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, RedirectAttributes rttr, MemberVO memberVO) throws Exception {
		
		HttpSession session = request.getSession();
		
		MemberVO loginResult = memberService.memberLogin(memberVO);
		
		if(loginResult != null) {
			loginResult.setMemberPw("");
			session.setAttribute("member", loginResult);
			return "redirect:/main";				
		}
		
		rttr.addFlashAttribute("result", 0);
		return "redirect:/goLogin";
		
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request) throws Exception {
		
		log.info("로그아웃 진행");
		HttpSession session = request.getSession();
		
		session.invalidate();
		log.info("세션 전체 삭제");
		
		return "redirect:/main";
	}
	
	// 비동기 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception {
		log.info("비동기 로그아웃 진행");
		
		HttpSession session = request.getSession();
		
		session.invalidate();
	}
	
	// 상품 검색
	@GetMapping("/search")
	public String SearchGoodsListGET(PageInfo pageInfo, Model model) throws Exception {
		
		List<BookVO> goodsList = bookService.getGoodsList(pageInfo);
		
		// 목록이 없을 경우
		if(goodsList.isEmpty()) {
			model.addAttribute("goodsListResult", "empty");
			return "/user/search";
		}
		
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", bookService.getDomesticCategoryCode());
		model.addAttribute("international", bookService.getInternationalCategoryCode());
		
		model.addAttribute("goodsListResult", goodsList);
		model.addAttribute("pagingManager", new PagingManager(pageInfo, bookService.getGoodsTotal(pageInfo)));
		
		return "/user/search";
	}
}
