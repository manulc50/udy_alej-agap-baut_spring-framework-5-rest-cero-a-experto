package com.devs4j.di.lifecycle.lazy;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Prueba de un bean de Spring de tipo Eager que depende de otro bean de Spring de tipo Lazy - Ambos se consideran como beans de tipo Eager

// Beans Eager y Lazy
// La creación de beans de Spring puede ser de tipo Eager o Lazy
// Los beans de Spring de tipo Eager se crean directamente aunque no se vayan a usar(inyectar)
// Los beans de Spring de tipo Lazy se crean cuando se solicitan al menos una vez(cuando se inyectan al menos una vez)
// Por defecto, los beans de Spring con scope "singleton"(scope por defecto de Spring) son de tipo Eager
// Por defecto, los beans de Spring con scope "prototype" son de tipo Lazy
// Nota: Si un bean singleton es lazy pero otro bean que depende de él no lo es, ambos serán considerados earger

// Por defecto, si no se pone la anotación @Lazy, es un bean de tipo Eager porque es un singleton
@Lazy(false) // @Lazy(false) = @Eager
@Component
public class BeanB {

	// Habilitamos el log en esta clase
	private static final Logger log = LoggerFactory.getLogger(BeanB.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private BeanA bean; // Dependencia con un bean de tipo BeanA
	
	@PostConstruct // Esta anotación hace que este método se ejecute justo después de la creación del bean(el momento de la creación del bean es durante la inyección de dependencias)
	public void init() {
		log.info("BeanB - Init bean B");
	}
}
