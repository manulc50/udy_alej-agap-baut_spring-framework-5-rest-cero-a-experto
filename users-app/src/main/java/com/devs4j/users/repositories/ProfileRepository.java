package com.devs4j.users.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.devs4j.users.entities.Profile;


public interface ProfileRepository extends CrudRepository<Profile, Integer>{
	
	// Consula personalizada que no se puede realizar usando Query Methods
	// Se implementan usando el lenguaje JPQL - Las consultas se hacen tomando como referencia las clases entidad y no las tablas de la base de datos
	// "?1" hace referencia al primer argumento de entrada del método, es decir, hace referencia a "Integer userId"
	// "?2" hace referencia al segundo argumento de entrada del método, es decir, hace referencia a "Integer profileId"
	@Query("SELECT p FROM Profile p WHERE p.user.id = ?1 AND p.id = ?2")
	Optional<Profile> findProfileByUserIdAndProfileId(Integer userId, Integer profileId);

}
