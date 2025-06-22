package com.anand.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anand.entity.Customer;
import com.anand.repo.CustomerRepo;

@Service
public class CustomerService implements UserDetailsService{
	
	@Autowired
	private BCryptPasswordEncoder  pwEncoder;

	@Autowired
	private CustomerRepo customerRepo;
	
	
	public boolean saveCustomer(Customer customer) {
		//before Storing encode the Password
		    String enCoded_Password=pwEncoder.encode(customer.getPassword());
		    //now the encoded password in to the database
		    customer.setPassword(enCoded_Password);
		//save the Customer in the database
	    	 Customer save_Customer = customerRepo.save(customer);
		return save_Customer.getCid()!=null;
	}

	
  /*method to load the user from the 
	    database 
	these is a abstracterMethod of UserDetailesService interface 
	it is called by the Spring security stater
	*/
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            System.out.println("inside the loadUserByUsername");
            /*first load the user
                by email Id From the database by using customerRepo
            */
            Customer byEmail = customerRepo.findByEmail(email);
            
            //pass these Customer in  User Object format
            return new User(byEmail.getEmail(),byEmail.getPassword(),Collections.emptyList());
	}
	
	
}
