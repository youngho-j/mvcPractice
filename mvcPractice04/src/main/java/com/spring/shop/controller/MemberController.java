package com.spring.shop.controller;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.shop.service.MemberService;
import com.spring.shop.util.AuthNumber;
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
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
		log.info("회원가입 데이터 전달");
		
		String password = "";
		String encodePassword = "";
		
		log.info("비밀번호 암호화");
		password = memberVO.getMemberPw();
		
		encodePassword = passwordEncoder.encode(password);
		
		memberVO.setMemberPw(encodePassword);
		
		memberService.memberJoin(memberVO);
		
		log.info("비밀번호 암호화 및 회원가입 성공");
		
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
		
		AuthNumber authNum = new AuthNumber();
		
		log.info("이메일 데이터 전송 확인 : " + email);
		
		int checkNum = authNum.getAuthNum();
		
		String sender = "ihaveneagong@naver.com";
        String recipient = email;
        String mailTitle = "회원가입 인증 이메일 입니다.";
        String mailContent = 
                "홈페이지를 방문해주셔서 감사합니다." +
                "<br><br>" + 
                "인증 번호는 " + checkNum + "입니다." + 
                "<br>" + 
                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        
        try {
        	MimeMessage message = mailSender.createMimeMessage();
        	MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        	
        	helper.setFrom(sender);
        	helper.setTo(recipient);
        	helper.setSubject(mailTitle);
        	helper.setText(mailContent, true);
        	
        	mailSender.send(message);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return Integer.toString(checkNum);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(HttpServletRequest request, RedirectAttributes rttr, MemberVO memberVO) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVO loginResult = memberService.memberLogin(memberVO);
		
		if(loginResult == null) {
			int result = 0;
			rttr.addFlashAttribute("result", result);
			return "redirect:/member/login";
		}
		
		session.setAttribute("member", loginResult);
		
		return "redirect:/main";
	}
}
