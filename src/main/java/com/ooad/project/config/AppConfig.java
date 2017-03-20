package com.ooad.project.config;

import com.ooad.project.service.OMDBApiService;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ooad")
public class AppConfig {

	@Bean
	public BasicHttpClientConnectionManager httpClientConnectionManager() {
		return new BasicHttpClientConnectionManager();
	}

	@Bean
	public HttpClientContext httpClientContext() {
		return HttpClientContext.create();
	}

	@Bean
	public HttpRoute httpRoute() {
		// create route to external service
		return new HttpRoute(new HttpHost("http://www.omdbapi.com/"));
	}

	@Bean
	public OMDBApiService omdbApiService() {
		return new OMDBApiService();
	}
}
