package com.coder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coder.model.EmailRequest;
import com.coder.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send-email")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest emailRequest){
		
		try {
			emailService.sendEmail(emailRequest);
			return new ResponseEntity("Email send successfully",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(" Email send Failed ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmailAttachment(@RequestParam String email, @RequestParam(required = false) MultipartFile[] file){
		
		try {
			emailService.sendEmailandAttachment(email, file);
			return new ResponseEntity("Email send successfully",HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(" Email send Failed ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
