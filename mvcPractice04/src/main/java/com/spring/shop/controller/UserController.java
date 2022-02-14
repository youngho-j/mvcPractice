package com.spring.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.shop.service.CategoryService;
import com.spring.shop.service.MemberService;
import com.spring.shop.util.MailManager;
import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	private CategoryService categoryService;
	
	private MemberService memberService;
	
	private JavaMailSender mailSender;
	
	@Autowired
	public UserController(CategoryService categoryService,
			MemberService memberService,
			JavaMailSender mailSender) {
		this.categoryService = categoryService;
		this.memberService = memberService;
		this.mailSender = mailSender;
	}

	// 메인 페이지 이동
	@GetMapping({"/main", "/"})
	public String mainPageGET(Model model, Authentication authentication) throws Exception {
		log.info("메인 페이지 진입");
		
		// spring security 정보 객체
		if(authentication != null) {
			log.info("회원 정보 존재");
			UserDetails memberVO = (UserDetails) authentication.getPrincipal();
			model.addAttribute("member", memberVO);
		}
		
		// 국내, 외 카테고리 목록
		model.addAttribute("domestic", categoryService.getDomesticCategoryCode());
		model.addAttribute("international", categoryService.getInternationalCategoryCode());
			
		return "user/main";
	}
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/goLogin", method = RequestMethod.GET)
	public String loginGET(String error) throws Exception {
		log.info("로그인 페이지 진입");
		log.info("원인 : " + error);
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
		
		boolean result = memberService.idCheck(memberId);
		
		if(result) {
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
		
		log.info("이메일 데이터 전송 확인 : {}", email);
		
		MailManager mailManager = new MailManager(mailSender);
		
		return mailManager.sendAuthEmail(email);
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost() throws Exception {
		log.info("로그인 진행");
	}
	
	// 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logoutPOST(HttpServletRequest request) throws Exception {
		log.info("로그아웃 진행");
	}
}