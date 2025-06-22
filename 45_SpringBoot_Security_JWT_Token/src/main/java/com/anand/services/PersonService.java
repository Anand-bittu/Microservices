package com.anand.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anand.model.Person;
import com.anand.repo.PersonRepo;

@Service
public class PersonService implements UserDetailsService {

	@Autowired
	private PersonRepo personRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public  boolean insertPerson(Person person) {
		//encode the password First
		  String Encoded_Password=passwordEncoder.encode(person.getPassword());
		   //save the encoded password
		  person.setPassword(Encoded_Password);
		  Person save = personRepo.save(person);  
		return save.getPid()!=null;
	}

	/*method to load the user from the 
    database 
      these is a abstracterMethod of UserDetailesService interface 
           it is called by the Spring security stater
*/
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         System.out.println("inside UserDetails ServicesLoadUserByUserName");
         //take the Person form the table
         Person byEmail = personRepo.findByEmail(email);
       //pass these Customer in  User Object format
         
		return new User(byEmail.getEmail(),byEmail.getPassword(),Collections.emptyList());
	}
	
	
}
