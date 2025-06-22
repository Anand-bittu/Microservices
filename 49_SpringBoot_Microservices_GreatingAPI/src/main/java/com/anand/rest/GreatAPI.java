package com.anand.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anand.welomeAPI.WelcomeFeginClientAPI;

@RestController
public class GreatAPI {
    @Autowired
	private WelcomeFeginClientAPI welcomeAPI;
	
    @GetMapping("/greating")
	public String greatingAPI() {
		String msg="good Evening";
		 String welcoemMessage=welcomeAPI.invokeWelcomeMsg();
		 return msg+" "+ welcoemMessage;	
	}
}
