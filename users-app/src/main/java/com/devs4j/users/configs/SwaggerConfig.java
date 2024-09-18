package com.devs4j.users.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // Anotamos esta clase de configuración como una clase de configuración de Spring
@EnableSwagger2
public class SwaggerConfig {
	
	// Método que crea una instancia de tipo Docket con la configuración de Swagger y la devuelve como un bean de Spring
	@Bean
	public Docket getDocket() {
		return new Docket(DocumentationType.SWAGGER_2) // Indicamos que el tipo de documentación sea la de Swagger2
				.apiInfo(apiInfo()) // Añadimos a la documentación la información personalizada devuelta por el método "apiInfo"
				.select()
				// Indicamos que queremos exponer en la documentación los controladores que se encuentren en el paquete "com.devs4j.users.controllers" de nuestra aplicación y todas sus rutas disponibles
				.apis(RequestHandlerSelectors.basePackage("com.devs4j.users.controllers"))
				// Por ejemplo, la expresión 'paths(PathSelectors.ant("/users/*"))' indica que sólo se expongan las rutas que coincidan con la expresión "/users/*"
				.paths(PathSelectors.any()).build()
				// Indicamos que no muestre en la documentación todos los códigos de respuestas http por defecto de un método handler.
				// Es decir, sólo queremos que en la documentación aparezca los códigos de respuesta indicados por nosotros en cada método handler de cada controlador 
				.useDefaultResponseMessages(false);
	}
	
	// Método que devuelve una instancia de tipo ApiInfo con información personalizada para añadir a la documentación
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Devs4j API REST")
				.version("1.0")
				.license("Apche 2.0")
				.contact(new Contact("@raidentrance","http://devs4j.com","contacto@devs4j.com"))
				.build();
	}

}
