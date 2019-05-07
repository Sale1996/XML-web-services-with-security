package com.project.userservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dtos.UserDTO;
import com.project.userservice.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserDTO> findAll(){
		
		return null;
	}
	
	public UserDTO findById(long id){
		
		return null;
	}
	
	public List<UserDTO> findByName(String username){
		
		return null;
	}
	
	public UserDTO update(long id, UserDTO agent){
		
		return null;
	}
	
	public UserDTO save(UserDTO user){
		
		return null;
	}
	
	public UserDTO delete(long id){
		
		return null;
	}
}
