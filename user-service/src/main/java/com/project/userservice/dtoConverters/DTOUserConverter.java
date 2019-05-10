package com.project.userservice.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.userservice.dtos.UserDTO;
import com.project.userservice.models.User;
import com.project.userservice.repositories.UserRepository;


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
				
		return u;
	}
	
}
