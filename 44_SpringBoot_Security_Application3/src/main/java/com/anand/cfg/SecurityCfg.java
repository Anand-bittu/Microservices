package com.anand.cfg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.anand.service.CustomerService;

@Configuration
@EnableWebSecurity
public class SecurityCfg {
 
	@Autowired
	public CustomerService customerService;
	
	//password Encoder
	@Bean
	public BCryptPasswordEncoder pwEncoder() {
		System.out.println("inside BCryptPasswordEncoder");
		return new BCryptPasswordEncoder();
	}
	//Configer SecurityFilterChain
	@Bean
	public SecurityFilterChain security(HttpSecurity httpSecurity) throws Exception{
	   //allow certain url
		httpSecurity.authorizeHttpRequests((req)->{
			req.requestMatchers("/register","/login")
			.permitAll()
			.anyRequest()
			.authenticated();
			
		});
		//enable csrf attack
		httpSecurity.csrf().disable();
		return httpSecurity.build();
	}
	
	@Bean
	//create a authoticate Provider
	public DaoAuthenticationProvider authProvider() {
		//create the dao auth provider
		DaoAuthenticationProvider daoprovider=
				new DaoAuthenticationProvider();
		//take bCrptEncoder
		daoprovider.setPasswordEncoder(pwEncoder());
		//take service class load customerMethod
		daoprovider.setUserDetailsService(customerService);
		return daoprovider;
	}
	
	//create AuthenticationManger to mange AuthProvider
	@Bean
	public AuthenticationManager authManger(AuthenticationConfiguration config)throws Exception {
		return config.getAuthenticationManager();
	}
}
