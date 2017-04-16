package com.ooad.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooad.project.config.AppConfig;
import com.ooad.project.service.OMDBApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class Application {
	@Bean
	public OMDBApiService omdbApiService() {
		return new OMDBApiService();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	public static void main(String[] args) throws Exception {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		SpringApplication.run(Application.class, args);
	}
}
