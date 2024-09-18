package com.devs4j.di.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

// Beans Eager y Lazy
// La creación de beans de Spring puede ser de tipo Eager o Lazy
// Los beans de Spring de tipo Eager se crean directamente aunque no se vayan a usar(inyectar)
// Los beans de Spring de tipo Lazy se crean cuando se solicitan al menos una vez(cuando se inyectan al menos una vez)
// Por defecto, los beans de Spring con scope "singleton"(scope por defecto de Spring) son de tipo Eager
// Por defecto, los beans de Spring con scope "prototype" son de tipo Lazy
// Nota: Si un bean singleton es lazy pero otro bean que depende de él no lo es, ambos serán considerados earger

// Las interfaces InitializingBean y DisposableBean funcionan del mismo modo que las anotaciones @PostConstruct y @PreDestroy respectivamente, es decir, son alternativas a las anotaciones @PostConstruct y @PreDestroy

@Component
@Lazy // Para probar cambiar el tipo Eager de un bean singleton al tipo Lazy
//@Scope("prototype") // Para probar que los beans con scope "prototype" no ejecutan los métodos anotados con @PreDestroy
public class LifeCycleBean implements BeanNameAware, InitializingBean, DisposableBean{
	
	// Habilitamos el log en esta clase
	private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

	// Sobrescribimos este método de la interfaz BeanNameAware
	// Este método recibe el nombre del bean y se ejecuta justo cuando Spring crea ese bean y le asigna el nombre 
	// En este caso, el nombre que se recibe es "lifeCycleBean", es decir, el nombre de esta clase comenzando en minúscula
	@Override
	public void setBeanName(String name) {
		log.info("LifeCycleBean - Bean name aware: {}", name);
		
	}
	
	// Los métodos que usan esta anotación pueden usar cualquier tipo de modificador de acceso(public, private, etc...)
	// Los métodos que usan esta anotación no deben recibir argumentos de entrada y tampoco deben devolver datos
	// Si un bean de Spring se crea de manera explicita usando la anotación @Bean, podemos usar su atributo "initMethod", a semejanza de esta anotación @PostConstruct, asignándole el nombre del método. Por ejemplo, @Bean(initMethod = "init") 
	// Nota: Esta anotación no es propia de Spring, sino que es una anotación de JSR-250 de Java y se suele usar también en EJBs
	@PostConstruct // Esta anotación hace que este método se ejecute justo después de la creación del bean(el momento de la creación del bean es durante la inyección de dependencias)
	public void init() {
		log.info("LifeCycleBean - Post construct");
	}
	
	// Los métodos que usan esta anotación pueden usar cualquier tipo de modificador de acceso(public, private, etc...)
	// Los métodos que usan esta anotación no deben recibir argumentos de entrada y tampoco deben devolver datos
	// Si un bean de Spring se crea de manera explicita usando la anotación @Bean, podemos usar su atributo "destroyMethod", a semejanza de esta anotación @PreDestroy, asignándole el nombre del método. Por ejemplo, @Bean(destroyMethod = "destroy") 
	// Nota: Esta anotación no es propia de Spring, sino que es una anotación de JSR-250 de Java y se suele usar también en EJBs
	// Nota: Los métodos anotados con esta anotación no se ejecutan cuando se usan beans con scope "prototype"
	// Nota: Los métodos anotados con esta anotación sólo se ejecutan si se termina la JVM(Java Virtual Machine) de manera normal
	@PreDestroy // Esta anotación hace que este método se ejecute justo antes de la destrucción del bean
	public void destroyBean() {
		log.info("LifeCycleBean - Pre destroy");
	}

	// Sobrescribimos este método de la interfaz DisposableBean
	// Este método de la interfaz DisposableBean se ejecute justo antes de la destrucción del bean
	// Es una alternativa al uso de la anotación @PreDestroy de JSR-250 de Java
	@Override
	public void destroy() throws Exception {
		log.info("LifeCycleBean - Destroy method");
		
	}

	// Sobrescribimos este método de la interfaz InitializingBean
	// Este método de la interfaz InitializingBean se ejecute justo después de la creación del bean(el momento de la creación del bean es durante la inyección de dependencias)
	// Es una alternativa al uso de la anotación @PostConstruct de JSR-250 de Java
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("LifeCycleBean - After properties set method");
		
	}

}
