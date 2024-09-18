package com.devs4j.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.Profile;
import com.devs4j.users.services.ProfileService;

@RestController
@RequestMapping("/users/{userId}/profiles") // Ruta base del controlador
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Profile> getProfileById(@PathVariable Integer userId, @PathVariable(name = "id") Integer profileId){
		return ResponseEntity.ok(profileService.getProfileById(userId,profileId));
	}

	@PostMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<Profile> createProfile(@RequestBody Profile profile, @PathVariable Integer userId){
		return new ResponseEntity<Profile>(profileService.create(profile, userId),HttpStatus.CREATED);
	}
}
