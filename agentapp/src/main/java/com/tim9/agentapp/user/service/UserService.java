package com.tim9.agentapp.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.agentapp.user.dto.UserDTO;
import com.tim9.agentapp.user.model.User;
import com.tim9.agentapp.user.repository.UserRepository;
import com.tim9.agentapp.user.utils.dtoConverter.DTOUserConverter;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	private final DTOUserConverter dtoUserConverter;
	
	public UserService(final UserRepository userRepository, final DTOUserConverter dtoUserConverter) {
		this.userRepository = userRepository;
		this.dtoUserConverter = dtoUserConverter;
	}
	
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
}
