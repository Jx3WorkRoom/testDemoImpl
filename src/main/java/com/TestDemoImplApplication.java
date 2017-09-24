package com;

import com.utils.JDBCTestDemo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TestDemoImplApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestDemoImplApplication.class, args);
	}
}
