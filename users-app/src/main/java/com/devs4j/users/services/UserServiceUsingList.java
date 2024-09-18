package com.devs4j.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.models.User;
import com.github.javafaker.Faker;

@Service
public class UserServiceUsingList {
	
	@Autowired
	private Faker faker; // Genera datos de ejemplo
	
	private List<User> users = new ArrayList<User>();
	
	// Esta anotación hace que este método se ejecute justo después de crearse el bean
	// Un método anotado con esta anotación no debe recivir argumentos de entrada y debe devolver siempre void
	// Usamos este método con esta anotación para inicializar el ArrayList de la propiedad "users" con datos de ejemplo una vez que el bean se haya creado
	@PostConstruct
	private void init() {
		for(int i=0;i<100;i++)
			users.add(new User(faker.funnyName().name(),faker.name().name(),faker.dragonBall().character()));
	}
	
	// El argumento de entrada "startWith" se corresponde con un Query Param opcional, es decir, puede estar o no en la url de la petición http
	public List<User> getUsers(String startWith){
		if(startWith != null)
			return users.stream().filter(u -> u.getUsername().startsWith(startWith)).collect(Collectors.toList());
		
		return users;
	}
	
	public User getUserByUsername(String username) {
		return users.stream().filter(user -> user.getUsername().equals(username)).findAny()
				// Si el usuario no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s not found",username)));
	}
	
	public User createUser(User user) {
		// Si en el ArrayList de usuario ya esiste un usuario con el mismo username que el usuario que se recibe como parámentro de entrada, hay un conflicto y lanzamos una excepción de tipo ResponseStatusException con el código de respuesta CONFILCT(409)  y un mensaje de error personalizado
		if(users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername())))
			throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("User %s already exists", user.getUsername()));
		
		users.add(user);
		return user;
	}
	
	public User updateUser(User user, String username) {
		User userToBeUpdated = getUserByUsername(username);
		
		userToBeUpdated.setNickName(user.getNickName());
		userToBeUpdated.setPassword(user.getPassword());
		// El username no se permite actualizar porque se usa como identificador único de los usuarios
		
		return userToBeUpdated;
	}
	
	public void deleteUser(String username) {
		User userToBeDeleted = getUserByUsername(username);
		users.remove(userToBeDeleted);
	}

}
