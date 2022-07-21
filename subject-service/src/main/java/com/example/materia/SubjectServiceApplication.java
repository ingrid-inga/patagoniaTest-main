package com.example.materia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SubjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubjectServiceApplication.class, args);
	}

}
