package com.spro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@SpringBootApplication
public class SeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeanApplication.class, args);
	}

	/**
	 * springboot 解决跨域访问问题
	 * @return
	 */
	@Bean
	public FilterRegistrationBean corsFilter(){
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		ArrayList<String> objects = new ArrayList<>();
		objects.add("*");
		config.setAllowedOrigins(objects);
		config.setAllowedHeaders(objects);
		config.setAllowedMethods(objects);
//        source.registerCorsConfiguration("/**", config);
		Map<String, CorsConfiguration> corsConfigurations = new HashMap<>();
		corsConfigurations.put("/**",config);
		source.setCorsConfigurations(corsConfigurations);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}
}
