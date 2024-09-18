package com.devs4j.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devs4j.users.entities.User;
import com.devs4j.users.entities.UserInRole;
import com.devs4j.users.repositories.UserInRoleRepository;
import com.devs4j.users.repositories.UserRepository;

@Service
public class Devs4jUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;


	// Implementamos este método de la interfaz UserDetailsService de Spring Security para recuperar el usuario y sus roles de la base de datos a partir del username que es pasado a este método por Spring Security y se corresponde con el username proporcionado por el usuario de la aplicación
	// Si este método crea y devuelve un usuario de Spring Security con los datos del usuario localizado en la base de datos y sus roles, significa que ese usuario está autenticado correctamente
	// Si este método lanza una excepción de tipo UsernameNotFoundException, significa que el usuario no se ha localizado en la base de datos y, por lo tanto, no es un usuario autenticado
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Buscamos al usuario en la base de datos a partir del username que es recibido como argumento de entrada en este método
		Optional<User> optional = userRepository.findByUsername(username);
		// Si el usuario es localizado, hacemos lo siguiente:
		if (optional.isPresent()) {
			// Obtenemos el usuario
			User user = optional.get();
			// Obtenemos los roles del usuario como una lista de objetos de tipo UserInRole
			List<UserInRole> userInRoles = userInRoleRepository.findByUser(user);
			// Convertimos la lista anterior en un array de Strings con los nombres de los roles del usuario
			String[] roles = userInRoles.stream()
								.map(uir -> uir.getRole().getName())
								.toArray(size -> new String[size]); //String[]::new
		// Creamos y devolvemos un usuario de Spring Security a partir de los datos obtenidos en el código anterior, es decir, a partir del username y de la contraseña de nuestro usuario recuperado de la base de datos y a partir de sus roles
		return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(passwordEncoder.encode(user.getPassword())) // La contraseña se codifica usando el codificador de contraseñas(BCrypt) configurado en la clase de configuración de Spring "SecurityConfig"
				.roles(roles)
				.build();
		}
		// Si el usuario no se encuentra en la base de datos, lanza una excepción de tipo UsernameNotFoundException de Spring Security con  un mensaje de error personalizado
		else 
			throw new UsernameNotFoundException("User with username " + username + " not found");

	}

}
