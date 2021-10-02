package com.spring.shop.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.shop.service.MemberService;
import com.spring.shop.util.AuthNumber;
import com.spring.shop.util.MailManager;
import com.spring.shop.vo.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	private MailManager mailManager;
	
	private AuthNumber authNum;
	
	// 로그인 페이지 이동
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception {
		log.info("로그인 페이지 진입");
	}
	
	// 회원가입 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void joinGET() throws Exception {
		log.info("회원가입 페이지 진입");
	}
	
	// 회원가입 후 페이지 이동
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPOST(MemberVO memberVO) throws Exception {
		
		memberService.memberJoin(memberVO);
		
		log.info("회원가입 성공");
		
		return "redirect:/main";
	}
	
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
	
	@RequestMapping(value = "/mailCheck", method = RequestMethod.GET)
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		
		log.info("이메일 데이터 전송 확인 : " + email);
		
		mailManager = new MailManager(mailSender);
		
		String check = mailManager.sendAuthEmail(email);
		
		return check;
	}
	
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
		return "redirect:/member/login";
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutGET(HttpServletRequest request) throws Exception {
		
		log.info("로그아웃 진행");
		HttpSession session = request.getSession();
		
		session.invalidate();
		log.info("세션 전체 삭제");
		
		return "redirect:/main";
	}
}
