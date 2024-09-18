package com.devs4j.di.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod") // Sólo se va a crear un bean de Spring de esta clase en el contexto de Spring cuando el perfil activo sea "prod"(Si no se indica ningún perfil, por defecto se utiliza el perfil "default")
public class ProdEnvironment implements EnvironmentService{

	@Override
	public String getEnvironment() {
		return "Prod";
	}

}
