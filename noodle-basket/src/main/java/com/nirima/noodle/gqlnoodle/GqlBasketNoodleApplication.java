package com.nirima.noodle.gqlnoodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GqlBasketNoodleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GqlBasketNoodleApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer mainCorsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")

						.allowedOrigins(
								"*",
								"https://studio.apollographql.com",
								"http://localhost:8080",
								"http://localhost:9002",
								"http://localhost:3000",
								"http://localhost:3001"
						)
						.allowedMethods("GET", "PUT", "POST", "OPTIONS", "DELETE")
						.allowedHeaders("*");

			}
		};
	}


}
