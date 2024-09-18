package com.devs4j.di.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Avion implements Volador {

	// Habilitamos el log en esta clase
	private static final Logger log = LoggerFactory.getLogger(Avion.class);

	
	@Override
	public void volar() {
		log.info("Soy un avión y estoy volando");
	}

}
