package com.devs4j.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.devs4j.users.entities.Address;
import com.devs4j.users.entities.Profile;
import com.devs4j.users.repositories.AddressRepository;
import com.devs4j.users.repositories.ProfileRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
	public List<Address> getAddressByUserIdAndProfileId(Integer userId, Integer profileId){
		return addressRepository.findByUserIdAndProfileId(userId, profileId);
	}
	
	public Address createAddress(Address address, Integer userId, Integer profileId) {
		Optional<Profile> oProfile = profileRepository.findProfileByUserIdAndProfileId(userId, profileId);
		// Si el profile no se encuentra, lanza una excepción de tipo ResponseStatusException con el código de respuesta NOT_FOUND(400) y un mensaje de error personalizado
		if(!oProfile.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Profile not found for user id %d and profile id %d",userId,profileId));
		
		address.setProfile(oProfile.get());
		return addressRepository.save(address);
	}
}
