package com.example.academy;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class AcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyApplication.class, args);
	}
	
	
	@PostConstruct
	public void intit() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

}
