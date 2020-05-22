package com.spro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication( exclude = SecurityAutoConfiguration.class)	//屏蔽冲突的包
public class SeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeanApplication.class, args);
	}

}
