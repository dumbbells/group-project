package com.ooad.project.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ooad.project.service.OMDBApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	public OMDBApiService omdbApiService() {
		return new OMDBApiService();
	}
}
