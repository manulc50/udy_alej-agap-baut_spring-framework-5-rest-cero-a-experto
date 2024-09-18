package com.devs4j.di.autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Square implements Figure{
	
	// Inyecta el valor de la propiedad "square.side" en esta propiedad que se encuentra en el archivo de propiedades "area.properties"
	// Si la propiedad "square.side" no se localiza o no existe, por defecto toma el valor 2
	@Value("${square.side:2}")
	private double side;

	@Override
	public double calcularArea() {
		return side * side;
	}

}
