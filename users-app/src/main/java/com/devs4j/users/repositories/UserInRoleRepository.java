package com.devs4j.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devs4j.users.entities.User;
import com.devs4j.users.entities.UserInRole;

public interface UserInRoleRepository extends CrudRepository<UserInRole, Integer>{
	
	// Query Mehtod
	public List<UserInRole> findByUser(User user);

	// Consula personalizada que no se puede realizar usando Query Methods
	// Se implementan usando el lenguaje JPQL - Las consultas se hacen tomando como referencia las clases entidad y no las tablas de la base de datos
	// "?1" hace referencia al primer y único argumento de entrada del método, es decir, hace referencia a "String roleName"
	@Query("SELECT uir.user FROM UserInRole uir WHERE uir.role.name=?1")
	public List<User> findUsersByRoleName(String roleName);
}
