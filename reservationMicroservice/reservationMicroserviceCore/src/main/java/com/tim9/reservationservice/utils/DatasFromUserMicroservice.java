package com.tim9.reservationservice.utils;

import org.springframework.stereotype.Component;

import com.tim9.userserviceClient.dtos.UserDTO;
import com.tim9.userserviceClient.feignClients.UserClient;

import feign.FeignException;

@Component
public class DatasFromUserMicroservice {

	private UserClient userClient;
	
	public DatasFromUserMicroservice(UserClient userClient) {
		this.userClient = userClient;
	}
	
	public UserDTO getById(Long id) {
		
		try {
			return userClient.getUserById(id);
		}
		catch(FeignException f) {			
			return new UserDTO();
		}
	}
}
