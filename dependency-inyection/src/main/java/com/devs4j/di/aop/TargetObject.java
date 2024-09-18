package com.devs4j.di.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// Esta clase va a ser el Join Point de los Aspectos "MyAspect" y "MyAspect2"

@Component
public class TargetObject {
	
	private static final Logger log = LoggerFactory.getLogger(TargetObject.class);

	@Devs4jAnnotation // Anotamos este método con nuestra anotación personalizada @Devs4jAnnotation para probar un caso de uso de un Point Cut
	public void hello(String message) {
		log.info(message);
	}
	
	public void foo() {
		log.info("foo");
	}

}
