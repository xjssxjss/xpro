package com.spro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeanApplication.class, args);
	}

}
