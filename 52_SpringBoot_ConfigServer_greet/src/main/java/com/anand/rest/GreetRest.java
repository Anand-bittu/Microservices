package com.anand.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//this annotion takes or reload config properties dynamically
@RefreshScope
public class GreetRest {

	@Value("${msg}")
	private String message;
	
	@GetMapping("/")
	public String getMessage() {
		return message;
	}
	
}
