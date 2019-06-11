package com.tim9.userservice.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.userservice.models.User;
import com.tim9.userservice.repositories.UserRepository;
import com.tim9.userserviceClient.dtos.UserDTO;

@Component
public class DTOUserConverter {
	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO convertToDTO(User user) {
		
		UserDTO dto = new UserDTO();
		
		dto.setId(user.getId());
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setEmail(user.getEmail());
		dto.setAddress(user.getAddress());
		dto.setActivated(user.getActivated());
		dto.setTelephoneNumber(user.getTelephoneNumber());
		dto.setRole(user.getRole());
		
		return dto;
	}
	
	public User convertFromDTO(UserDTO dto) {
		
		Optional<User> user = userRepository.findById(dto.getId());
		
		if(user.isPresent()) {
			
			return user.get();
		}
		
		User u = new User();
		
		u.setId(dto.getId());
		u.setFirstName(dto.getFirstName());
		u.setLastName(dto.getLastName());
		u.setEmail(dto.getEmail());
		u.setActivated(dto.getActivated());
		u.setAddress(dto.getAddress());
		u.setTelephoneNumber(dto.getTelephoneNumber());
		u.setPassword(dto.getPassword());
		u.setRole(dto.getRole());
				
		return u;
	}
	
}
