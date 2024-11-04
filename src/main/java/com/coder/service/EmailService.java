package com.coder.service;

import org.springframework.web.multipart.MultipartFile;

import com.coder.model.EmailRequest;

public interface EmailService {

	public Boolean sendEmail(EmailRequest emailRequest) throws Exception;
	public void sendEmailandAttachment(String email, MultipartFile[] files)throws Exception; // isme hmm multi file bhi ek sath send kr skta hai
}
