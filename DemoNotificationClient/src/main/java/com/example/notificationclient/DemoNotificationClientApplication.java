package com.example.notificationclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoNotificationClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoNotificationClientApplication.class, args);
	}

}
