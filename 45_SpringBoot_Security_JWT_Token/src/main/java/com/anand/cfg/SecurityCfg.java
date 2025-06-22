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

import com.anand.services.PersonService;

@Configuration
@EnableWebSecurity
public class SecurityCfg {

	@Autowired
	public PersonService services;
	
	//Configer SecurityFilterChain
	@Bean
	public SecurityFilterChain securityFilter(HttpSecurity httpSecurity)throws Exception {
		System.out.println("inside SecurityFilterChain");
		httpSecurity.authorizeHttpRequests((req)->{
			req.requestMatchers("/reg","log")
			.permitAll()
			.anyRequest()
			.authenticated();
		});
		
		//enable csrf attack
		httpSecurity.csrf().disable();
		return httpSecurity.build();
	}	
	//password Encoder
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		System.out.println("inside BCryptPassswordEncoder");
	 return new BCryptPasswordEncoder();
	}
	
	@Bean
	//create a authoticate Provider
	//it taken two thing one is services and other one is passwordEncoder
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		//create the daoAuthenticationProvider
		DaoAuthenticationProvider provider=new
				DaoAuthenticationProvider();
		//take service class load findByPersonEmail
		provider.setUserDetailsService(services);
		//take bCrptEncoder
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	//create AuthenticationManger to mange AuthProvider
	@Bean
	public AuthenticationManager authManger(AuthenticationConfiguration cfg) throws Exception{
		return cfg.getAuthenticationManager();
	}
	
}
