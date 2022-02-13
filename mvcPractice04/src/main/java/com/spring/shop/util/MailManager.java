package com.spring.shop.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailManager {
	
	private JavaMailSender mailSender;
	
	private final String sender = "ihaveneagong@naver.com";
	
	public MailManager(JavaMailSender mailSender) throws Exception {
		this.mailSender = mailSender;
	}
	
	public String sendAuthEmail(String email) throws Exception {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper 
			= new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		// 인증 번호
		int checkNum = new AuthNumber().getAuthNum();
		
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setTo(email);
		mimeMessageHelper.setSubject("회원가입 인증 이메일 입니다.");
		mimeMessageHelper.setText(			
	                "홈페이지를 방문해주셔서 감사합니다." +
	                "<br><br>" + 
	                "인증 번호는 <mark><big><b>" + checkNum + "</b></big></mark> 입니다." + 
	                "<br><br>" + 
	                "해당 인증번호를 인증번호 확인란에 기입하여 주세요."
	                , true);
        
        mailSender.send(mimeMessage);
		
		return Integer.toString(checkNum);
	}
}
