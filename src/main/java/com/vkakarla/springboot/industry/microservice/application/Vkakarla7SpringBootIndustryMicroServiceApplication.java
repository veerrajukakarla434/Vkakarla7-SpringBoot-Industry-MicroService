package com.vkakarla.springboot.industry.microservice.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(value="com.vkakarla.springboot.industry.microservice.*")
@EnableSwagger2
public class Vkakarla7SpringBootIndustryMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Vkakarla7SpringBootIndustryMicroServiceApplication.class, args);
	}
	
	
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.vkakarla.springboot.industry.microservice.controller"))
	      .build();
	}
	
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
