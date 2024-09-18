package com.devs4j.di.autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circle implements Figure{

	// Inyecta el valor de la propiedad "circle.radius" en esta propiedad que se encuentra en el archivo de propiedades "area.properties"
	// Si la propiedad "circle.radius" no se localiza o no existe, por defecto toma el valor 1
	@Value("${circle.radius:1}")
	private double radius;
	
	@Override
	public double calcularArea() {
		return Math.PI * Math.pow(radius, 2);
	}

}
