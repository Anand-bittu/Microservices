package com.anand.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/gm")
	public String getMessage() {
		return "Gm";
	}
}
