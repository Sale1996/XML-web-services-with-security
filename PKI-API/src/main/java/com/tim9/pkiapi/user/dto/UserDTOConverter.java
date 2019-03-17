package com.tim9.pkiapi.user.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.user.model.User;
import com.tim9.pkiapi.user.repository.UserRepository;

@Component
public class UserDTOConverter {
	
	@Autowired
	UserRepository userRepository;

	public UserDTO convertToDTO ( User user ) { 
		
		UserDTO dto = new UserDTO();
		
		dto.setEmail(user.getEmail());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setId(user.getId());
	
		return dto;
		
	}
	
	public User convertFromDTO ( UserDTO dto ) {
		
		Optional<User> user = userRepository.findById(dto.getId());
		
		if(user.isPresent()) {
			return user.get();
		}
		
		User newUser =  new User();
		
		newUser.setEmail(dto.getEmail());
		newUser.setFirstName(dto.getFirstName());
		newUser.setLastName(dto.getLastName());
		newUser.setId(dto.getId());
		
		return newUser;
		
	}
}
