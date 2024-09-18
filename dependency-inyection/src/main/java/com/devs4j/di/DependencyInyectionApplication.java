package com.devs4j.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.devs4j.di.aop.TargetObject;
import com.devs4j.di.autowired.AreaCalculatorService;
import com.devs4j.di.inyection.Coche;
import com.devs4j.di.lifecycle.ExplicitBean;
import com.devs4j.di.lifecycle.LifeCycleBean;
import com.devs4j.di.profiles.EnvironmentService;
import com.devs4j.di.qualifiers.Animal;
import com.devs4j.di.qualifiers.Avion;
import com.devs4j.di.qualifiers.Nido;
import com.devs4j.di.qualifiers.Pajaro;
import com.devs4j.di.qualifiers.Perro;
import com.devs4j.di.scopes.EjemploScopeService;


@SpringBootApplication
public class DependencyInyectionApplication {
	
	// Habilitamos el log en esta clase
	private static final Logger log = LoggerFactory.getLogger(DependencyInyectionApplication.class);

	// Forma explicita de crear beans de Spring - Se usa la anotación @Bean
	// Esta anotación crea un bean de Spring con el resultado de lo que devuelve un método y lo almacena en el contenedor, o memoria, de Spring
	// En este caso, el método crea y devuelve el String "Devs4j Rules!" y, medinate esta anotación, se almacena en el contenedor de Spring como un bean
	@Bean
	public String getApplicationName() {
		return "Devs4j Rules!";
	}
	
	// Prueba del ciclo de vida de un bean de Spring
	// Forma explicita de crear beans de Spring - Se usa la anotación @Bean
	// Esta anotación crea un bean de Spring con el resultado de lo que devuelve un método y lo almacena en el contenedor, o memoria, de Spring
	// En este caso, el método crea y devuelve una instancia de tipo ExplicitBean y, medinate esta anotación, se almacena en el contenedor de Spring como un bean
	// Cuando se crea un bean de Spring de manera explicita, podemos usar el atributo "initMethod" de la anotación @Bean para indicarle que ejecute un método de ese bean justo después de su creación
	// En este caso, indicamos que se ejecute el método "init" de la clase ExplicitBean justo después de que Spring cree el bean de esa clase
	// Cuando se crea un bean de Spring de manera explicita, podemos usar el atributo "destroyMethod" de la anotación @Bean para indicarle que ejecute un método de ese bean justo antes de su destrucción
	// En este caso, indicamos que se ejecute el método "destroy" de la clase ExplicitBean justo antes de que Spring destruya el bean de esa clase
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public ExplicitBean getBean() {
		return new ExplicitBean();
	}
	
	public static void main(String[] args) {
		// Obtenemos el contexto de Spring
		ConfigurableApplicationContext context =  SpringApplication.run(DependencyInyectionApplication.class, args);
		// Obtenemos el bean de tipo Coche almacenado en el contenedor de Spring
		Coche coche = context.getBean(Coche.class);
		System.out.println(coche);
		// Obtenemos del contenedor, o memoria, de Spring el bean que implemente la interfaz EnvironmentService
		EnvironmentService environmentService = context.getBean(EnvironmentService.class);
		// Mostramos en el log a modo de información el environment activado 
		log.info("Active environtment {}", environmentService.getEnvironment());
		
		// Prueba de los scopes(singleton y prototype) de Spring
		// Por defecto, Spring utiliza el scope singleton
		// Obtenemos el bean de tipo EjemploScopeService almacenado en el contenedor, o memoria, de Spring
		EjemploScopeService ejemploScopeService1 = context.getBean(EjemploScopeService.class);
		// Volvemos a Obtener el bean de tipo EjemploScopeService almacenado en el contenedor, o memoria, de Spring
		EjemploScopeService ejemploScopeService2 = context.getBean(EjemploScopeService.class);
		// Usando el scope por defecto singleton, ambas instanias tienen el mismo bean y, por lo tanto, las comparaciones de contenido y de referencia van a ser true
		// Usando el scope por defecto protorype, ambas instanias tienen beans diferentes y, por lo tanto, las comparaciones de contenido y de referencia van a ser false
		log.info("Are beans equals?: {}", ejemploScopeService1.equals(ejemploScopeService2));
		log.info("Are beans equals?: {}", ejemploScopeService1 == ejemploScopeService2);
		
		// Prueba de la creación explicita de beans de Spring
		// Obtenemos el bean de tipo String almacenado en el contenedor, o memoria, de Spring
		String applicationName = context.getBean(String.class);
		log.info("Nombre de la aplicación: {}", applicationName);
		
		// Prueba de inyección directa de múltiples beans
		// Obtenemos el bean de tipo AreaCalculatorService almacenado en el contenedor, o memoria, de Spring
		AreaCalculatorService calculator = context.getBean(AreaCalculatorService.class);
		log.info("Area total: {}", calculator.calcAreas());
		
		// Prueba de una evaluación de expresiones con SpEL
		ExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("10 + 30");
		log.info("Result: {}", expression.getValue());
		
		// Prueba del ciclo de vida de un bean de Spring
		// Obtenemos del contenedor, o memoria, de Spring el bean que implemente la interfaz BeanNameAware. Este bean es de tipo LifeCycleBean
		@SuppressWarnings("unused")
		BeanNameAware lifeCycleBean = context.getBean(LifeCycleBean.class);
		
		// Pruebas de AOP(Aspect Object Programming)
		// Obtenemos el bean de tipo TargetObject almacenado en el contenedor, o memoria, de Spring
		TargetObject targetObject = context.getBean(TargetObject.class);
		targetObject.hello("Hello world");
		targetObject.foo();
		
		// Prueba del uso de Qualifiers
		// Obtenemos el bean de tipo Perro almacenado en el contenedor, o memoria, de Spring
		Perro perro = context.getBean(Perro.class);
		log.info("Perro: {}", perro.getNombre());
		
		// Obtenemos el bean de tipo Pajaro almacenado en el contenedor, o memoria, de Spring
		Pajaro pajaro = context.getBean(Pajaro.class);
		log.info("Pajaro: {}", pajaro.getNombre());
		
		// Obtenemos el bean de tipo Avion almacenado en el contenedor, o memoria, de Spring
		Avion avion = context.getBean(Avion.class);
		avion.volar();
		
		// Obtenemos el bean de tipo Avion almacenado en el contenedor, o memoria, de Spring
		// Como las clases Perro y Pajaro heredan la clase Animal y son componentes de Spring porque están anotadas con @Componente, existen 2 candidatos que se pueden inyectar aquí pero Spring no sabe cuál de ellos debe inyectar
		// De esta manera, se produce un error de ejecución con el mensaje "No qualifying bean of type 'com.devs4j.di.qualifiers.Animal' available: expected single matching bean but found 2: pajaro,perro ..."
		// Para solucionar este error, debemos indicar a Spring qué implementación deseamos inyectar de esos 2 candidatos y, para ello, al método "getBean" le pasamos el nombre del bean de la implementación, que, por defecto, va a ser el nombre de la clase("Pajaro") comenzando en minúscula("pajaro")
		// Ahora pasamos el nombre "pajarito" al método "getBean" porque así hemos llamado al bean de la clase Pajaro que se almacena en el contenedor de Spring(Clase Pajaro anotada con @Componenet("pajarito"))
		Animal animal = context.getBean("pajarito",Animal.class);
		log.info("Animal: Nombre={}, Edad={}", animal.getNombre(), animal.getEdad());
		
		// Obtenemos el bean de tipo Nido almacenado en el contenedor, o memoria, de Spring
		Nido nido = context.getBean(Nido.class);
		nido.imprimir();
		
		/*
		// SIN USAR INYECCIÓN DE DEPENDENCIAS DE SPRING
		Motor motor = new Motor("Xl1", 1981);
		Coche coche = new Coche("VW", 1986, motor);
		System.out.println(coche);*/
	}

}
