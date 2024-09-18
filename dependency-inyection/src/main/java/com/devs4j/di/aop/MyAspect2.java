package com.devs4j.di.aop;

import java.lang.reflect.Modifier;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect // Esta anotación indica que esta clase es un Aspecto
@Order(0) // Como tenemos 2 Aspectos que tienen el mismo Join Point(clase "TargetObject"), con esta anotación podemos establecer el orden de ejecución de esos Aspectos
@Component
public class MyAspect2 {

	private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

	// Esta anotación que se corresponde con un Advice de tipo Before
	// El lenguaje usado en las anotaciones que son Advice se denomina "Pointcut expression language"
	// La expresión "(..)" indica que no importa el número de argumentos de entrada que reciban los métodos de la clase "TargetObject"
	// Podemos acceder a la información del Join Point pasando un argumento de entrada de tipo "JoinPoint" al método
	// Si hay varios Aspectos que utilizan el mismo Point Cut(misma expresión "Pointcut expression language"), como es este Aspecto junto con el Aspecto "MyAspect2", podemos especificar en las anotaciones de los Advices un Point Cut común que haga uso de esa misma expresión escrita en "Pointcut expression language"
	// De esta forma, evitamos repetir la misma expresión "Pointcut expression language" en varios Aspectos y únicamente la definimos en una único sitio
	//@Before("execution(* com.devs4j.di.aop.TargetObject.*(..))") // Interfiere cualquier método de la clase "TargetObject" del paquete "com.devs4j.di.aop" que devuelva cualquier tipo de dato y que reciba cualquier número de argumentos de entrada y hace que este método se ejecute antes que cualquier método de esa clase
	//@Before("execution(* com.devs4j.di.aop.TargetObject.hello(..))") // Interfiere al método "hello" de la clase "TargetObject" del paquete "com.devs4j.di.aop" que devuelve cualquier tipo de dato y que recibe cualquier núnmero de argumentos de entrada y hace que este método se ejecute antes que ese método de esa clase
	@Before("PointcutExample.targetObjectMethods()") // Este Advice de tipo Before utiliza la expresión "Pointcut expression language" del Point Cut común que se localiza en el método "targetObjectMethods" de la clase "PointcutExample"
	public void before(JoinPoint joinpoint) {
		log.info("1 Advice Before");
		log.info("1 Method name: {}", joinpoint.getSignature().getName());
		log.info("1 Object type: {}", joinpoint.getSignature().getDeclaringTypeName());
		log.info("1 Modifiers: {}", joinpoint.getSignature().getModifiers());
		log.info("1 Is public?: {}", Modifier.isPublic(joinpoint.getSignature().getModifiers()));
		log.info("1 Arguments: {}", joinpoint.getArgs());
	}
}
