package com.devs4j.di.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutExample {
	
	//@Pointcut("execution(* com.devs4j.di.aop.TargetObject.hello(..))") // Interfiere al método "hello" de la clase "TargetObject" del paquete "com.devs4j.di.aop" que devuelve cualquier tipo de dato y que recibe cualquier núnmero de argumentos de entrada
	//@Pointcut("within(com.devs4j.di.aop.*)") // Interfiere cualquier método de cualquier clase que se encuentre dentro del paquete "com.devs4j.di.aop"
	//@Pointcut("within(com.devs4j.di.aop.TargetObject)") // Interfiere cualquier método de la clase TargetObject que se encuentre dentro del paquete "com.devs4j.di.aop"
	//@Pointcut("within(TargetObject)") // Si la clase que representa el Target Object se encuentra en el mismo paquete que el Point Cut, podemos poner directamente el nombre de esa clase. Interfiere cualquier método de la clase TargetObject
	@Pointcut("@annotation(Devs4jAnnotation)") // Interfiere cualquier método anotado con la anotación @Devs4jAnnotation de cualquier clase de la aplicación
	public void targetObjectMethods() {
		
	}
}
