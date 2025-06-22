package com.anand.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CircuitBreakerDesign {

	@GetMapping("/data")
	/*
	 * This annotation is used for CircuitBreaker 
	 * Design patter  for fallbacking to fall back
	 * method
	 */
	@CircuitBreaker(fallbackMethod ="getDataFromDataBase",name = "redies")
	public String getDataFromRedies() {
		System.out.println("inside Redies method");
		  int i=10/0;
		//logic to Communicate with Redies server
		return "Fetch data From Redies Server";
	}
	
	public String getDataFromDataBase(Throwable th) {
		System.out.println("inside DataFromDataBase");
		//logic to communicate with  the database
		return "Fetch data from the DataBase";
	}
	
}
