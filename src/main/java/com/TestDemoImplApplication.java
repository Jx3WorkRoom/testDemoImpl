package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestDemoImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDemoImplApplication.class, args);
	}
}
