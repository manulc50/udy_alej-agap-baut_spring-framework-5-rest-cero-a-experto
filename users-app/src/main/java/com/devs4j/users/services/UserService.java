package com.devs4j.users.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.User;
import com.devs4j.users.repositories.UserRepository;

@Service
public class UserService {
	
	// Habilitamos el logger en esta clase
	private static Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;
	
	// Devuelve users usando paginación
	public Page<User> getUsers(int page, int size) {
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	// Devuelve usernames usando paginación
	public Page<String> getUsernames(int page, int size){
		return userRepository.findUsernames(PageRequest.of(page, size));
	}
	
	public User getUserById(Integer idUser) {
		// Si el user no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
		return userRepository.findById(idUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with id %d doesn't exist",idUser)));
	}
	
	@Cacheable("users") // El usuario devuelto por este método va a ser almacenado en la caché "users"(Esta caché se creó en la clase de configuración de Spring "CacheConfig")
	public User getUserByUsername(String username) {
		log.info("Getting user by username {}", username);
		// Para ver mejor el comportamiento de la caché, interrumpimos la ejecución de este método durante 3 seg para simular un método que tarda en ejecutarse
		// De esta manera, la primera vez que se ejecute este método para un username tardará 3 seg, pero las siguientes veces que se ejecute este método para el mismo usarname tardará mucho menos tiempo porque el user con ese username se obtendrá de la caché "users" en lugar de ejecutarse este método
		try {
			Thread.sleep(3000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		Optional<User> oUser = userRepository.findByUsername(username);
		// Si el user no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
		if(!oUser.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with username %s doesn't exist",username));
		
		return oUser.get();
	}
	
	// Esta anotación nos permite eliminar datos existentes en una caché
	// En este caso, como el método anterior "getUserByUsername" almacena un "user" en la caché "users" cuando se obtiene por su username, si eliminamos ese mismo "user" de la base de datos usando este método, también queremos que se elimina de la caché "users"
	@CacheEvict("users")
	public void deleteUserByUsername(String username) {
		User user = getUserByUsername(username);
		userRepository.delete(user);
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		// Si el user no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
		return userRepository.findByUsernameAndPassword(username, password)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with username %s and password %s doesn't exist", username, password)));
	}
	
	
}
