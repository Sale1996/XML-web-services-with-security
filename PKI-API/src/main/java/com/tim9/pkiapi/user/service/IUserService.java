package com.tim9.pkiapi.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tim9.pkiapi.user.dto.UserDTO;

@Service
public interface IUserService {
	
	public UserDTO findOneById ( Long id );
	
	public UserDTO findOneByEmail ( String email );
	
	public List<UserDTO> findAll();
	
	public UserDTO save ( UserDTO userToSave );
	
	public UserDTO deleteById ( Long id );
	
}
