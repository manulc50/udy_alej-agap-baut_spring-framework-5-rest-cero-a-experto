package com.devs4j.users.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Anotamos esta clase de configuración como una clase de configuración de Spring
@EnableWebSecurity
// Esta anotación con los atributos "securedEnabled", "jsr250Enabled" y "prePostEnabled" puestos a true nos permite habilitar las anotaciones "@Secured" "@RolesAllowed" y "@PreAuthorize" y "@PostAuthorize" respectivamente para usarlas en los métodos handler de los controladores(también es posible usarlas en los métodos de la capa de servicio) y así poder controlar y manejar el acceso o ejecución de estos métodos en función de los roles de un usuario autenticado
// Nota: La anotación "@Secured" es propia de Spring Security, sin embargo, la anotación "@RolesAllowed" es propia del estandar JSR250 de Java. Ambas anotaciones tienen la misma funcionalidad y son dos posibles alternativas para manejar el control o la ejecución de los métodos en función de los roles de un usuario autenticado
// Nota: La funcionalidad de la anotación "@PreAuthorize" es identica a la funcionalidad de las anotaciones "@Secured" y "@RolesAllowed", es decir, nos permiten controlar la ejecucción de los métodos en función de los roles de un usuario autenticado. Sin embargo, la diferencia de la anotación "@PreAuthorize" con respecto a las otras 2, es que esta anotación evalua expresiones de tipo SpEL
// Nota: La anotación "@PostAuthorize" evalua expresiones SpEL al final de la ejecución de los métodos y permite al usuario autenticado ejecutar esos métodos pero sólo va a obtener la respuesta de esos métodos si su role está presente en esta anotación "@PostAuthorize"
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	/*
	// COMENTAMOS ESTE CÓDIGO PORQUE AHORA LA AUTENTICACIÓN DE USUARIOS SE REALIZA USANDO UNA BASE DE DATOS Y NO EN MEMORIA
	// Sobrescribimos este método de la clase padre WebSecurityConfigurerAdapter para implementar nuestra autenticación en memoria
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Autenticación en memoria
		// Creamos el usuario "admin" con la contraseña "password" codificada según la implementación del método "encode" y le damos el role ADMIN
		// Creamos el usuario "user" con la contraseña "password" codificada según la implementación del método "encode" y le damos el role USER
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(encoder().encode("password"))
			.roles("ADMIN")
			.and()
			.withUser("user")
			.password(encoder().encode("password"))
			.roles("USER");
	}*/
	
	
	// Sobrescribimos este método de la clase padre WebSecurityConfigurerAdapter para configurar la seguridad de las rutas y de los recursos de nuestra aplicación
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // Desactivamos el uso de CSRF
			// Permite restringir el acceso a rutas o recursos de una aplicación
			.authorizeRequests()
			// El método "antMatchers" define las rutas o recursos a proteger
			// Los usuarios que accedan a las rutas de nuestra aplicación que coincidan con la expresión "/v2/users/**"("**" significa todo to que haya a continuación) necesitan tener el role "ADMIN" 
			.antMatchers("/v2/users/**").hasRole("ADMIN")
			// Todo usuario autenticado correctamente puede acceder a la rutas que coincidan con la expresión "/roles/**"("**" significa todo to que haya a continuación)
			.antMatchers("/roles/**").authenticated()
			.and()
			// Habilitamos el uso de autenticación básica
			.httpBasic();
	}



	// Método que crea un codificador de contraseñas usando la implementación BCryptPasswordEncoder y lo devuelve como un bean de Spring
	// La implementación BCryptPasswordEncoder usa la codificación BCrypt
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	
	

}
