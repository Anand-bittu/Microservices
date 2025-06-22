package com.anand.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestAPi {
	
	@Autowired
	private Environment envirolment;

	@GetMapping("/welcome")
	public String welcomePage() {
		String port=envirolment.getProperty("server.port");
		return "welcome API Message"+"("+port+")";
	}
}
