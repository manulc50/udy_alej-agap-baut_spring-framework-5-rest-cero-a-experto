package com.devs4j.users.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration // Anotamos esta clase de configuración como una clase de configuración de Spring
public class FakerBeanConfig {
	
	@Bean
	public Faker getFaker() {
		return new Faker();
	}

}
