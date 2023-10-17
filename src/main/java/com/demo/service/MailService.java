package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender; 
	
	@Autowired
	private TemplateEngine templateEngine;
	
	public void sendEmailToClient(String to, String subject, Context body,String templateName) {
        SimpleMailMessage message = new SimpleMailMessage();
        String html = templateEngine.process(templateName, body);
        message.setFrom("nishkarshmohan.sinha@gmail.com");
        message.setTo("nishkarshmohan.sinha@gmail.com");
        message.setSubject(subject);
        message.setText(html);

        javaMailSender.send(message);
    }
	
	
	
}
