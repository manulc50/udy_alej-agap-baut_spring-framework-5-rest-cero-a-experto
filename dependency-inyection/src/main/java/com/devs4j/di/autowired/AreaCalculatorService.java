package com.devs4j.di.autowired;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Inyección directa de múltiples beans

@Service
public class AreaCalculatorService {
	
	// Todos los beans que están en el contenedor, o memoria, de Spring e implementan la interfaz Figure(clases Square y Circle) van a ser inyectados dentro de la lista de esta propiedad
	@Autowired
	private List<Figure> figures;
	
	public double calcAreas() {
		double areas = 0;
		for(Figure f: figures)
			areas += f.calcularArea();
		return areas;
	}
}
