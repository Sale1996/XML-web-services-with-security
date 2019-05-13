package com.project.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dtoConverters.DTOUserConverter;
import com.project.userservice.dtos.UserDTO;
import com.project.userservice.models.User;
import com.project.userservice.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DTOUserConverter dtoUserConverter;
	
	public List<UserDTO> findAll(){
		
		Optional< List<User> > users = Optional.of (userRepository.findAll());
		
		ArrayList<UserDTO> dtoUsers = new ArrayList<UserDTO>();
		
		if (users.isPresent()) {
			
			for (User u : users.get()) {
				
				dtoUsers.add(dtoUserConverter.convertToDTO(u));			
			}
			
			return dtoUsers;			
		}
			
		return Collections.emptyList();
	}
	
	public UserDTO findById(long id){
		
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			
			return dtoUserConverter.convertToDTO(user.get());	
		}
		
		return new UserDTO();	
	}
	
	public UserDTO update(long id, UserDTO user){
		
		Optional<User> userForChange = userRepository.findById(id);
		
		if(userForChange.isPresent() && user != null) {
										
			userForChange.get().setFirstName(user.getFirstName());
			userForChange.get().setLastName(user.getLastName());
			userForChange.get().setEmail(user.getEmail());
			userForChange.get().setPassword(user.getPassword());
			userForChange.get().setAddress(user.getAddress());
			userForChange.get().setActivated(user.getActivated());
			userForChange.get().setTelephoneNumber(user.getTelephoneNumber());
			userForChange.get().setRole(user.getRole());
	
			userRepository.save(userForChange.get());
			
			user.setId(userForChange.get().getId());
					
			return user;		
		}
		
		return new UserDTO();
	}
	
	public UserDTO save(UserDTO user){
		
		user.setId(-1l);
		
		User u = userRepository.save(dtoUserConverter.convertFromDTO(user));
		
		user.setId(u.getId());
		
		return user;
	}
	
	public UserDTO delete(long id){
		
		Optional<User> userToDelete = userRepository.findById(id);
		
		if(userToDelete.isPresent()) {
			
			userRepository.deleteById(id);
			
			return dtoUserConverter.convertToDTO(userToDelete.get());		
		}
		
		return new UserDTO();
	}
}
