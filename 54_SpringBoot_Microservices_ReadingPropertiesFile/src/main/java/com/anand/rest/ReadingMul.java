package com.anand.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.properties.AppProperites;

@RestController
public class ReadingMul {

	@Autowired
	private AppProperites message;
	
	@GetMapping("/welcome")
	public String getWelcome() {
		Map<String,String> mp=message.getMessage();
		 String msg=mp.get("welcome_msg");
		return  msg;
	}
	
	@GetMapping("/greet")
	public String getGreating() {
		Map<String,String> mp=message.getMessage();
		  String  msg=mp.get("greet_msg");
		  return msg;
	}
	
}
