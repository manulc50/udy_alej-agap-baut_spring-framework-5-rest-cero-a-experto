package com.devs4j.users.controllers;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.Role;
import com.devs4j.users.entities.User;
import com.devs4j.users.models.Devs4jSecurityRule;
import com.devs4j.users.services.RoleService;

@RestController
@RequestMapping("/roles") // Ruta base del controlador
public class RoleController {
	
	// Habilitamos el logger en esta clase
	private static Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	// La clase SecurityContextHolder nos permite acceder a los datos del usuario autenticado 
	@GetMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<List<Role>> getRoles(){
		// Obtenemos el contexto de autenticación de Spring Security
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Usamos el contexto de autenticación para mostrar en el log a modo de información algunos datos sobre el usuario autenticado
		log.info("Name: {}", authentication.getName()); // Muestra el username del usuario
		log.info("Principal: {}", authentication.getPrincipal()); // Principal es un objeto que contiene datos del usuario autenticado, como su username y sus roles, datos sobre la cuenta de ese usuario, etc...
		log.info("Credentials: {}", authentication.getCredentials());
		log.info("Roles: {}", authentication.getAuthorities().toString()); // Muestra los roles del usuario
		List<Role> roles = (List<Role>)roleService.getRoles();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	// Con las anotaciones "@Secured" y "@RolesAllowed" indicamos que este método handler sólo puede ser ejecutado por un usuario autenticado que tenga el role "ADMIN"
	// Usar llaves {} en las anotaciones "@Secured" y "@RolesAllowed" si se desea especificar más de un role como un array de roles
	//@Secured("ROLE_ADMIN") // Anotación de Spring Security
	//@RolesAllowed("ROLE_ADMIN") // Anotación del estandar de JSR250 Java. Alternativa a la anotación de arriba "@Secured". Ambas tienen la misma funcionalidad
	// Con la anotación "@PreAuthorize"(misma funcionalidad que las notaciones "@Secured" y "@RolesAllowed") indicamos que este método handler sólo puede ser ejecutado por un usuario autenticado que tenga el role "USER" o el role "ADMIN"
	// Para usar alguna de estas anotaciones, siempre se tiene que especificar el prefijo "ROLE_" delate del nombre del role
	//@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // A diferencia de las anotaciones "@Secured" y "@RolesAllowed", esta anotación evalua expresiones de tipo SpEL
	// La anotación "@PostAuthorize" se evalua al final de la ejecución de un método y permite ejecutar ese método(si no se indica la anotación "@PreAuthorize" o si se indica y el role del usuario está presente en esa anotación) al usuario autenticado pero únicamente devuelve la respuesta de ese método si el role de ese usuario está presente en esta anotación "@PostAuthorize" 
	// En caso contrario, el usuario autenticado puede ejecutar el método pero no va a recibir la respuesta de ese método
	// Al igual que la anotación "@PreAuthorize", esta anotación "@PostAuthorize" también evalua expresiones SpeEL 
	//@PostAuthorize("hasRole('ROLE_ADMIN')") // En este caso, sólo se devuelve la respuesta del método al usuario autenticado que tenga el role ADMIN
	// Anotación de seguridad propia que combina las anotaciones "@PreAuthorize" y "@PostAuthorize"
	@Devs4jSecurityRule
	@GetMapping("/{rolename}/users")
	public ResponseEntity<List<User>> getUsersByRoleName(@PathVariable String rolename){
		log.info("Getting role by name {}", rolename);
		return ResponseEntity.ok(roleService.getUsersByRoleName(rolename));
	}
	
	@PostMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<Role> createRole(@RequestBody Role role){
		return new ResponseEntity<Role>(roleService.createRole(role), HttpStatus.CREATED);
	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable(name = "roleId") Integer id){
		return new ResponseEntity<Role>(roleService.updateRole(role, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRole(@PathVariable Integer id){
		roleService.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
