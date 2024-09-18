package com.devs4j.di.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class Devs4jBeanPostProcessor implements BeanPostProcessor{

	// Habilitamos el log en esta clase
	private static final Logger log = LoggerFactory.getLogger(Devs4jBeanPostProcessor.class);
	
	// Sobrescribimos este método de la interfaz BeanPostProcessor
	// Este método nos permite implementar una lógica antes de que Spring cree un bean(Se aplica a todos los beans de Spring que se creen en la aplicación, tantos los nuestros como otros de la aplicación)
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// Sólo escribe en el log a modo de información si el bean de Spring es de tipo LifeCycleBean
		if(bean instanceof LifeCycleBean)
			log.info("Before initialization: {}", beanName);
		return bean;
	}

	// Sobrescribimos este método de la interfaz BeanPostProcessor
	// Este método nos permite implementar una lógica después de que Spring cree un bean(Se aplica a todos los beans de Spring que se creen en la aplicación, tantos los nuestros como otros de la aplicación)
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// Sólo escribe en el log a modo de información si el bean de Spring es de tipo LifeCycleBean
		if(bean instanceof LifeCycleBean)
			log.info("After initialization: {}", beanName);
		return bean;
	}
	
	

}
