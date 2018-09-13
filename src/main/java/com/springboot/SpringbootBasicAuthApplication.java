package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.springboot.basicauth"})
public class SpringbootBasicAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBasicAuthApplication.class, args);
	}
}
