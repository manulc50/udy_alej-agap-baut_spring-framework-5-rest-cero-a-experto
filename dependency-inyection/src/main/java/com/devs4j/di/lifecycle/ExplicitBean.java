package com.devs4j.di.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExplicitBean {
	
	// Habilitamos el log en esta clase
	private static final Logger log = LoggerFactory.getLogger(ExplicitBean.class);

	// Método que se ejecutará justo después de que Spring cree un bean de esta clase
	// Este tipo de método no debe recibir argumentos de entrada y tampoco debe devolver datos
	public void init() {
		log.info("ExplicitBean - init method");
	}
	
	// Método que se ejecutará justo antes de que Spring destruya un bean de esta clase
	// Este tipo de método no debe recibir argumentos de entrada y tampoco debe devolver datos
	public void destroy() {
		log.info("ExplicitBean - destroy method");
	}

}
