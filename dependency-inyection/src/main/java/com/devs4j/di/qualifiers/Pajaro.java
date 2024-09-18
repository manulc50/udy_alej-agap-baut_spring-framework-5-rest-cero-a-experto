package com.devs4j.di.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// Como tenemos dos componentes de Spring, esta clase y la clase Perro, que heredan la clase Animal, con esta anotación indicamos a Spring que el bean de esta clase, que se crea en su contenedor, o memoria, va a ser el bean por defecto que se va a inyectar cuando se solicite un bean de tipo Animal
// Si en la inyección del bean de tipo Animal hay un Qualifier, Spring no tendrá en cuenta esta anotación @Primary e inyectará la implementación indicada en ese Qualifier
@Primary
@Component("pajarito") // Se almacena un bean de esta clase en el contendor, o memoria, de Spring con el nombre de "pajarito"
public class Pajaro extends Animal implements Volador {
	
	// Habilitamos el log en esta clase
	private static Logger log = LoggerFactory.getLogger(Pajaro.class);

	public Pajaro(@Value("2") Integer edad, @Value("Pájaro Loco") String nombre) {
		super(edad, nombre);
	}
	
	@Override
	public void volar() {
		log.info("Soy un pájaro y estoy volando");
	}

}
