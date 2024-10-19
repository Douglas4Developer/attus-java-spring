package com.doug.attus;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableProcessApplication("AttusApplication")
@EnableFeignClients
public class AttusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttusApplication.class, args);
	}

}
