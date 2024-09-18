package com.devs4j.di.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"}) // Sólo se va a crear un bean de Spring de esta clase en el contexto de Spring cuando el perfil activo sea "dev" o cuando no haya ningún perfil activo indicado(Si no se indica ningún perfil, por defecto se utiliza el perfil "default")
public class DevEnvironment implements EnvironmentService{

	@Override
	public String getEnvironment() {
		return "Dev";
	}

}
