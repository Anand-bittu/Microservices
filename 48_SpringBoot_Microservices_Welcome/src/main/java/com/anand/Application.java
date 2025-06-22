package com.anand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication

/*
 *    This annotation enable use to register my welcome microserves 
 *    with service Register
 */
@EnableDiscoveryClient  
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
