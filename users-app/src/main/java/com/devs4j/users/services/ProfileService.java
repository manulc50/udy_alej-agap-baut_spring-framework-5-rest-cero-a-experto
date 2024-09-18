package com.devs4j.users.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.Profile;
import com.devs4j.users.entities.User;
import com.devs4j.users.repositories.ProfileRepository;
import com.devs4j.users.repositories.UserRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Profile getProfileById(Integer userId, Integer profileId) {
		return profileRepository.findProfileByUserIdAndProfileId(userId, profileId)
				// Si el profile no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profile not found for user id %d and profile id %d",userId, profileId)));
	}
	
	public Profile create(Profile profile, Integer userId) {
		Optional<User> oUser = userRepository.findById(userId);
		// Si el user no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
		if(!oUser.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User with id %d doesn't exist",userId));
		else {
			profile.setUser(oUser.get());
			return profileRepository.save(profile);
		}
	}

}
