package com.devs4j.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

// Esta clase de configuración de Spring es para que se tenga en cuenta el archivo de propiedades personalizado "areas.properties" en el arranque de esta aplicación Spring Boot
// Y, de esta manera, poder inyectar los valores de algunas propiedades definidas en este archivo en algunos atributos de algunas clases de la aplicación
// Nota: Si las propiedadedes se definen en el archivo de propiedades por defecto de una aplicación Spring Boot, "application.properties", no hace falta crear esta clase de configuración de Spring para realizar la inyección de dependencias  

@Configuration
@PropertySource("classpath:areas.properties")
public class FigurePropertyConfiguration {

	// Forma explicita de crear beans de Spring - Se usa la anotación @Bean
	@Bean
	public PropertySourcesPlaceholderConfigurer getPropertiesConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
