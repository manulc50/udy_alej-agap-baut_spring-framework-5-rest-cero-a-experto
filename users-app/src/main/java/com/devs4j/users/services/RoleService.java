package com.devs4j.users.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.Role;
import com.devs4j.users.entities.User;
import com.devs4j.users.models.AuditDetails;
import com.devs4j.users.repositories.RoleRepository;
import com.devs4j.users.repositories.UserInRoleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserInRoleRepository userInRoleRepository;
	
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	// Para serializar objetos de clases de nuestro modelo a objetos JSON
	private ObjectMapper mapper = new ObjectMapper();
	
	public Iterable<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	public List<User> getUsersByRoleName(String rolename){
		return userInRoleRepository.findUsersByRoleName(rolename);
	}
	
	public Role createRole(Role role) {
		Role roleCreated = roleRepository.save(role);
		// Obtenemos el username del usuario autenticado usando la información del objeto de tipo Principal del contexto de Spring Security
		String principalName = SecurityContextHolder.getContext().getAuthentication().getName();
		// Creamos un objeto de tipo AuditDetails, a partir del username del usuario autenticado y del nombre del role que acaba de crear este usuario autenticado, para enviarlo al tópico "devs4j-topic" de nuestro broker de Kafka
		AuditDetails details = new AuditDetails(principalName, roleCreated.getName());
		try {
			// Enviamos el objeto de tipo AuditDetails al tópico "devs4j-topic" de nuestro broker de Kafka
			// Para ello, previamente al envío, serializamos este objeto como un JSON en formato String 
			kafkaTemplate.send("devs4j-topic", mapper.writeValueAsString(details));
		}
		// Si el proceso de serialización lanza una excepción de tipo JsonProcessingException, la capturamos para lanzar una excepción de tipo ResponseStatusException con el código de respuesta BAD_REQUEST(400) y un mensaje de error personalizado
		catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error parsing the message");
		}
		return roleCreated;
	}

	public Role updateRole(Role role, Integer id) {
		// Si el role no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(404) y un mensaje de error personalizado
		Role roleDB = roleRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role with id %d doesn't exist",id)));
		roleDB.setName(role.getName());
		return roleRepository.save(roleDB);
	}
	
	public void deleteRole(Integer id) {
		Optional<Role> oRole = roleRepository.findById(id);
		// Si el role no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(404) y un mensaje de error personalizado
		if(!oRole.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Role with id %d doesn't exist",id));
		
		roleRepository.delete(oRole.get());
			
	}
}
