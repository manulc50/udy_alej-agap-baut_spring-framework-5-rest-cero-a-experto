package com.devs4j.users.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.devs4j.users.entities.User;


public interface UserRepository extends PagingAndSortingRepository<User, Integer>{
	
	// Query Method
	public Optional<User> findByUsername(String username);
	
	// Query Method
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
	// Consula personalizada que no se puede realizar usando Query Methods
	// Se implementan usando el lenguaje JPQL - Las consultas se hacen tomando como referencia las clases entidad y no las tablas de la base de datos
	@Query("SELECT u.username FROM User u")
	public Page<String> findUsernames(Pageable pageable);

}
