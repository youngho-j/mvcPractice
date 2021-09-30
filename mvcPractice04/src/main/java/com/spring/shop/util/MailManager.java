package com.spring.shop.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailManager {
	
	private JavaMailSender mailSender;
	
	private MimeMessage message;
	
	private MimeMessageHelper helper;
	
	private AuthNumber authNum;
	
	public MailManager(JavaMailSender mailSender) throws Exception{
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		helper = new MimeMessageHelper(message, true, "utf-8");
	}
	
	public String sendAuthEmail(String email) throws Exception{
		
		int checkNum = 0;
		String sender = "";
        String recipient = "";
        String mailTitle = "";
        String mailContent = "";
		
		authNum = new AuthNumber();
		
		checkNum = authNum.getAuthNum();
			
		sender = "ihaveneagong@naver.com";
	    recipient = email;
	    mailTitle = "회원가입 인증 이메일 입니다.";
	    mailContent = 
	                "홈페이지를 방문해주셔서 감사합니다." +
	                "<br><br>" + 
	                "인증 번호는 " + checkNum + "입니다." + 
	                "<br>" + 
	                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        	
        helper.setFrom(sender);
        helper.setTo(recipient);
        helper.setSubject(mailTitle);
        helper.setText(mailContent, true);
			
        mailSender.send(message);
		
		return Integer.toString(checkNum);
	}
}
