package com.devs4j.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devs4j.users.entities.Address;
import com.devs4j.users.services.AddressService;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/addresses") // Ruta base del controlador
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<List<Address>> getAddressesByProfileIdAndUserId(@PathVariable Integer userId, @PathVariable Integer profileId){
		return new ResponseEntity<List<Address>>(addressService.getAddressByUserIdAndProfileId(userId, profileId), HttpStatus.OK);
	}
	
	@PostMapping // Si no se especifica una ruta, por defecto es la ruta base "/"
	public ResponseEntity<Address> createAddress(@RequestBody Address address, @PathVariable Integer userId, @PathVariable Integer profileId){
		return new ResponseEntity<Address>(addressService.createAddress(address, userId, profileId), HttpStatus.CREATED);
	}

}
