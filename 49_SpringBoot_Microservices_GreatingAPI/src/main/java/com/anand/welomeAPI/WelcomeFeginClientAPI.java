package com.anand.welomeAPI;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Welcome-service")
public interface WelcomeFeginClientAPI {

	@GetMapping("/welcome")
	public String invokeWelcomeMsg();
	
	
}
