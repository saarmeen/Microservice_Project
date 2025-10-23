package com.example.authenticationser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationserApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationserApplication.class, args);
	}

}
