package com.devs4j.users.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello") // Ruta base del controlador
public class Devs4jController {
	
	@GetMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public String helloWorld() {
		return "Hello world!";
	}

}
