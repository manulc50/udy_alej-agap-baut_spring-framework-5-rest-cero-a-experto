package com.devs4j.di.inyection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component // Almacena una instancia de esta clase como un bean de Spring en el contenedor, o memoria, de Spring
public class Coche {
	
	// Inyección de dependencias a través de atributos o propiedades de una clase
	//@Value("VW") // Inyecta el valor "VW" en esta propiedad
	private String marca;
	
	// Inyección de dependencias a través de atributos o propiedades de una clase
	//@Value("1986") // Inyecta el valor "1986" en esta propiedad(Automáticamente convierte el String "1986" a un Integer)
	private Integer modelo;
	
	// Inyección de dependencias a través de atributos o propiedades de una clase
	//@Autowired // Inyecta un bean de tipo Motor del contenedor, o memoria, de Spring
	private Motor motor;
	
	// El constructor vacío es necesario para Spring si queremos realizar inyección de dependencias usando métodos setter
	public Coche() {
		
	}
	
	// Inyección de dependencias a través del constructor de una clase
	// "@Value("VW")" inyecta el valor "VW" en el parámetro de entrada "marca" de este constructor
	// "@Value("1986")" inyecta el valor "1986" en el parámetro de entrada "modelo" de este constructor
	// "@Autowired" inyecta un bean de tipo Motor del contenedor, o memoria, de Spring en el parámetro de entrada "motor" de este constructor
	//@Autowired // Lo podemos omitir si en la clase sólo tenemos un único constructor con los parámetros de entrada donde queremos hacer la inyección de dependencias. Si la clase tiene más de un constructor, tenemos que usar esta anotación en el constructor donde queremos hacer la inyección de dependencias
	public Coche(/*@Value("VW")*/ String marca, /*@Value("1986")*/ Integer modelo, Motor motor) {
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
	}

	public String getMarca() {
		return marca;
	}

	// Inyección de dependencias a través del método setter de una propiedad de una clase
	@Value("VW") // Inyecta el valor "VW" en el parámetro de entrada "marca" de este método setter
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getModelo() {
		return modelo;
	}

	// Inyección de dependencias a través del método setter de una propiedad de una clase
	@Value("1986") // Inyecta el valor "1986" en el parámetro de entrada "modelo" de este método setter
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public Motor getMotor() {
		return motor;
	}

	// Inyección de dependencias a través del método setter de una propiedad de una clase
	@Autowired // Inyecta un bean de tipo Motor del contenedor, o memoria, de Spring en el parámetro de entrada "motor" de este método setter
	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	@Override
	public String toString() {
		return "Coche [marca=" + marca + ", modelo=" + modelo + ", motor=" + motor + "]";
	}
	
}
