package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.*"})
@EntityScan(basePackages = {"com.example.entity"})
@EnableJpaRepositories (basePackages = {"com.*"})
public class Workout2Application {

	public static void main(String[] args) {
		SpringApplication.run(Workout2Application.class, args);
	}

}
