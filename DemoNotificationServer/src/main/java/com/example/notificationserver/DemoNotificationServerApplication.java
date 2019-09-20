package com.example.notificationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoNotificationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNotificationServerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/timecheck").allowedOrigins("http://localhost:8080");
				registry.addMapping("/zengcode-websocket/**").allowedOrigins("http://localhost:8080");
				registry.addMapping("/notifications").allowedOrigins("http://localhost:8080");
				registry.addMapping("/updatenotification").allowedOrigins("http://localhost:8080");
				registry.addMapping("/app_notification").allowedOrigins("http://localhost:8080");
				registry.addMapping("/app_muilnotification").allowedOrigins("http://localhost:8080");

			}
		};
	}
}
