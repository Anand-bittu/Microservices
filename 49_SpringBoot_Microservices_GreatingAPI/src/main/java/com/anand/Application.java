package com.anand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
/*
 *    This annotation enable use to register my GreatAPI microserves 
 *    with service Register
 */
@EnableDiscoveryClient
/*
 * this annotation enables FeginClient  depenedcy in GreatAPI 
 * for interservices Communication
 */
@EnableFeignClients
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
