package com.devs4j.di.inyection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // Almacena una instancia de esta clase como un bean de Spring en el contenedor, o memoria, de Spring
public class Motor {
	
	// Inyección de dependencias a través de atributos o propiedades de una clase
	//@Value("Xl1")  // Inyecta el valor "Xl1" en esta propiedad
	private String marca;
	
	// Inyección de dependencias a través de atributos o propiedades de una clase
	//@Value("1981") // Inyecta el valor "1981" en esta propiedad(Automáticamente convierte el String "1981" a un Integer)
	private Integer modelo;
	
	// El constructor vacío es necesario para Spring si queremos realizar inyección de dependencias usando métodos setter
	public Motor() {
		
	}
	// Inyección de dependencias a través del constructor de una clase
	// "@Value("Xl1")" inyecta el valor "Xl1" en el parámetro de entrada "marca" de este constructor
	// "@Value("1981")" inyecta el valor "1981" en el parámetro de entrada "modelo" de este constructor
	public Motor(/*@Value("Xl1")*/ String marca, /*@Value("1981")*/ Integer modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}
	
	// Inyección de dependencias a través del método setter de una propiedad de una clase
	@Value("Xl1") // Inyecta el valor "Xl1" en el parámetro de entrada "marca" de este método setter
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public Integer getModelo() {
		return modelo;
	}
	
	// Inyección de dependencias a través del método setter de una propiedad de una clase
	@Value("1981") // Inyecta el valor "1981" en el parámetro de entrada "modelo" de este método setter
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	@Override
	public String toString() {
		return "Motor [marca=" + marca + ", modelo=" + modelo + "]";
	}
	
}
