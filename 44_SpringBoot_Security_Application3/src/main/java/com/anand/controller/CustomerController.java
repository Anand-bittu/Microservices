package com.anand.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anand.entity.Customer;
import com.anand.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AuthenticationManager authManger;
	
	
	@PostMapping("/register")
	public ResponseEntity<String> save_Customer_In_DataBase(@RequestBody Customer customer){
		//call service class Object Method
		boolean saveCustomer = customerService.saveCustomer(customer);
		if(saveCustomer) {
			return new ResponseEntity<>("Saved the Customer::",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Error Message::",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginPage(@RequestBody Customer customer){
		System.out.println("logic to login page");
		UsernamePasswordAuthenticationToken token=
				new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());
		       //pass a token to the authmanger
		        //verfiy given email is valid or not 
		        Authentication authenticate = authManger.authenticate(token);
		   boolean status=authenticate.isAuthenticated();
		   if(status) {
			   return new ResponseEntity<String>("LoginScucessfull",HttpStatus.ACCEPTED);
		   }else {
		        return new ResponseEntity<String>("LoginFailed",HttpStatus.BAD_REQUEST);
		   }
	}
	
	
	
}
