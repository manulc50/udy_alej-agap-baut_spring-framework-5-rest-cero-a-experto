package com.devs4j.di.scopes;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

// Por defecto, Spring utiliza el scope singleton
// El scope Singleton crea una sola instancia del bean en el contendor, o memoria, de Spring y, siempre que se solicite, entrega la misma instancia 
// El scope Prototype crea una nueva instancia del bean por cada solicitud que se haga

@Service
@Scope("prototype") // Cambiamos el scope por defecto "singleton" por el scope "prototype"
public class EjemploScopeService {

}
