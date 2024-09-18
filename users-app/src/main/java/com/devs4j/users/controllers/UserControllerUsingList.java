package com.devs4j.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.models.User;
import com.devs4j.users.services.UserServiceUsingList;

@RestController
@RequestMapping("/v1/users") // Ruta base del controlador
public class UserControllerUsingList {
	
	@Autowired
	private UserServiceUsingList userService;

	@GetMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String startWith){
		return new ResponseEntity<List<User>>(userService.getUsers(startWith), HttpStatus.OK);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	@PostMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<User> createUser(@RequestBody User user){
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping("/{uname}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "uname") String username){
		return ResponseEntity.ok(userService.updateUser(user, username));
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username){
		userService.deleteUser(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
