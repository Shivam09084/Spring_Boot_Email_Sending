package com.coder.model;

import lombok.Data;

@Data
public class EmailRequest {
	
	private String title;
	private String  subject;
	private String body;
	private String recipentemail;

}
