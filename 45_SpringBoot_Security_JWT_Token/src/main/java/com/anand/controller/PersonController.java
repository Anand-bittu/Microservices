package com.anand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anand.model.Person;
import com.anand.services.PersonService;

@RestController
public class PersonController {

	@Autowired
	private PersonService service;
	
	@Autowired
	private AuthenticationManager authManger;
	
	@PostMapping("/reg")
	public ResponseEntity<String> registerPerson(@RequestBody Person person) {
		System.out.println("inside person");
		    boolean insertPerson = service.insertPerson(person);
		    if(insertPerson) {
		    	return new ResponseEntity<String>("Person Inserted!!!",HttpStatus.CREATED);
		    }else {
		    	return new ResponseEntity<String>("failed",HttpStatus.CONFLICT);
		    }
	}
	@PostMapping("/log")
	public ResponseEntity<String> loginPerson(@RequestBody Person person){
		System.out.println("inside Person Login");
		//pass as a token for authManger
		UsernamePasswordAuthenticationToken token=
				new UsernamePasswordAuthenticationToken(person.getEmail(), person.getPassword());
		  //verfiy given email is valid or not 
		Authentication authenticate = authManger.authenticate(token);
		   boolean status = authenticate.isAuthenticated();
		  if(status) {
			  return new ResponseEntity<String>("Login Succesfull",HttpStatus.OK);
		  }
		  else {
			  return new ResponseEntity<String>("Login failed||",HttpStatus.BAD_REQUEST);
		  }
	 
	}
	
	
}
