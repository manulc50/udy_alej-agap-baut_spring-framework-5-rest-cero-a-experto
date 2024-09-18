package com.devs4j.users.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devs4j.users.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer>{
	
	// Consula personalizada que no se puede realizar usando Query Methods
	// Se implementan usando el lenguaje JPQL - Las consultas se hacen tomando como referencia las clases entidad y no las tablas de la base de datos
	// "?1" hace referencia al primer argumento de entrada del método, es decir, hace referencia a "Integer userId"
	// "?2" hace referencia al segundo argumento de entrada del método, es decir, hace referencia a "Integer profileId"
	@Query("SELECT a FROM Address a WHERE a.profile.user.id = ?1 AND a.profile.id = ?2")
	List<Address> findByUserIdAndProfileId(Integer userId, Integer profileId);

}
