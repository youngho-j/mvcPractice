package com.spring.shop.util;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailManager {
	
	private JavaMailSender mailSender;
	
	private MimeMessage message;
	
	private MimeMessageHelper helper;
	
	private AuthNumber authNum;
	
	private String sender;
	
	public MailManager(JavaMailSender mailSender) throws Exception{
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		helper = new MimeMessageHelper(message, true, "utf-8");
		sender = "ihaveneagong@naver.com";
	}
	
	private void setMailContent(String from, String to, String title, String body) throws Exception {
		helper.setFrom(sender);
        helper.setTo(to);
        helper.setSubject(title);
        helper.setText(body, true);
	}
	
	public String sendAuthEmail(String email) throws Exception{
		
		int checkNum = 0;
		
        String mailTitle = "";
        String mailContent = "";
		
		authNum = new AuthNumber();
		
		checkNum = authNum.getAuthNum();
			
	    mailTitle = "회원가입 인증 이메일 입니다.";
	    mailContent = 
	                "홈페이지를 방문해주셔서 감사합니다." +
	                "<br><br>" + 
	                "인증 번호는 <mark><big><b>" + checkNum + "</b></big></mark> 입니다." + 
	                "<br><br>" + 
	                "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        	
        setMailContent(sender, email, mailTitle, mailContent);
			
        mailSender.send(message);
		
		return Integer.toString(checkNum);
	}
}
