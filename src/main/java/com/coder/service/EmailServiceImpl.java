package com.coder.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coder.model.EmailRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public Boolean sendEmail(EmailRequest emailRequest) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("roadie084@gmail.com", emailRequest.getTitle());
		helper.setTo(emailRequest.getRecipentemail());
		helper.setSubject(emailRequest.getSubject());
		helper.setText(emailRequest.getBody(),true);
		mailSender.send(message);
		
		return true;
	}

	@Override
	public void sendEmailandAttachment(String email, MultipartFile[] files) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		EmailRequest emailRequest = mapper.readValue(email, EmailRequest.class);
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);  // true tb use krta hai jb hmm file send kr rha ho 
		
		helper.setFrom("roadie084@gmail.com", emailRequest.getTitle());
		helper.setTo(emailRequest.getRecipentemail());
		helper.setSubject(emailRequest.getSubject());
		helper.setText(emailRequest.getBody(),true);
		
		for(MultipartFile file:files) {
			if(file != null) {
				ByteArrayResource bResource = new ByteArrayResource(file.getBytes());
				helper.addAttachment(file.getOriginalFilename(), bResource);
			}
		}
		mailSender.send(message);	
	}

}
