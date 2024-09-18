package com.devs4j.users.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.User;
import com.devs4j.users.services.UserService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/v2/users") // Ruta base del controlador
public class UserController {

	@Autowired
	private UserService userService;
	
	// Devuelve users usando paginación
	// Los parámetros "page" y "size" de la url son opcionales. Si no se indican, se establece los valores 0 y 10 por defecto para los parámetros "page" y "size" respectivamente
	@Timed("get.users") // Anotación para crear la métrica "get.users" para tomar mediciones sobre este método handler cuando maneje sus correspondientes peticiones http
	@GetMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size){
		return new ResponseEntity<Page<User>>(userService.getUsers(page,size), HttpStatus.OK);
	}
	
	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUsernames(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size){
		return ResponseEntity.ok(userService.getUsernames(page,size));
	}
	
	// Devuelve usernames usando paginación
	// Los parámetros "page" y "size" de la url son opcionales. Si no se indican, se establece los valores 0 y 10 por defecto para los parámetros "page" y "size" respectivamente
	// La anotación "@ApiOperation" de Swagger2 nos permite dar una información personalizada a un método handler de un controlador
	// En este caso, usando la anotación "@ApiOperation", ponemos la descripción "Return an user for a given user id" e indicamos que este método handler devuelve un dato de tipo User en la respuesta de la petición http
	// La anotación "@ApiResponses" de Swagger2 nos permite especificar los códigos de las respuestas de las peticiones http que puede devolver este método handler junto con una descipción del significado de cada código de respuesta
	// En este caso, usando la anotación "@ApiResponses", indicamos que este método handler devuelve respuestas de peticiones http con códigos OK(200) o con códigos 404(NOT_FOUND)
	@ApiOperation(value ="Return an user for a given user id", response = User.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "The user was found"),
			@ApiResponse(code = 404, message = "The user was not found")
			})
	@GetMapping("/{idUser}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "idUser") Integer id){
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username){
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	// Ejemplo para probar un Query Method(No es un caso real)
	@PostMapping("/authenticate")
	public ResponseEntity<User> authenticate(@RequestBody User user){
		return new ResponseEntity<User>(userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
	}
	
	@DeleteMapping("/username/{user_name}")
	public ResponseEntity<Void> deleteUserByUsername(@PathVariable(name = "user_name") String username){
		userService.deleteUserByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
