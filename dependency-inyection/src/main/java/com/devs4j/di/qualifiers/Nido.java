package com.devs4j.di.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Nido {
	
	// Habilitamos el log en esta clase
	private static Logger log = LoggerFactory.getLogger(Nido.class);

	// Como las clases Perro y Pajaro heredan la clase Animal y son componentes de Spring porque están anotadas con @Componente, existen 2 candidatos que se pueden inyectar en este astributo pero Spring no sabe cuál de ellos debe inyectar
	// De esta manera, se produce un error de ejecución con el mensaje "No qualifying bean of type 'com.devs4j.di.qualifiers.Animal' available: expected single matching bean but found 2: pajaro,perro ..."
	// Para solucionar este error, debemos indicar a Spring qué implementación deseamos inyectar de esos 2 candidatos y, para ello, usamos la anotación @Qualifier sobre este atributo con el nombre del bean de la implementación, que, por defecto, va a ser el nombre de la clase("Pajaro") comenzando en minúscula("pajaro")
	// Ahora pasamos el nombre "pajarito" a la anotación @Qualifier porque así hemos llamado al bean de la clase Pajaro que se almacena en el contenedor de Spring(Clase Pajaro anotada con @Componenet("pajarito"))
	// Como ahora la clase Pajaro tiene la anotación @Primary convirtiéndose en el bean por defecto de Spring para inyectarlo en solicitudes de tipo Animal, si no usamos la anotación @Qualifier, Spring inyectará el bean de clase Pajaro
	// Si en la inyección del bean de tipo Animal hay un Qualifier, Spring no tendrá en cuenta la anotación @Primary de la clase Pajaro e inyectará la implementación indicada en ese Qualifier
	//@Qualifier("pajarito")
	@Autowired
	private Animal animal;
	
	public void imprimir() {
		log.info("El nido tiene al animal: {}", animal.getNombre());
	}
}
