package com.devs4j.hello.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world") // Ruta base del controlador
public class HelloWorldController {
	
	@GetMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok("Hello World!");
	}

}
