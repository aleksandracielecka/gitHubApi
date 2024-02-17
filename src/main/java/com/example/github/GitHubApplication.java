package com.example.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GitHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(GitHubApplication.class, args);}

		@Bean
		public ObjectMapper objectMapper() {
			return new ObjectMapper();
		}
	}


