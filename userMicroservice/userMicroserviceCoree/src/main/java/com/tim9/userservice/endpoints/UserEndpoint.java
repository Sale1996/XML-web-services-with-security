package com.tim9.userservice.endpoints;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.userservice.models.GetUsersRequest;
import com.tim9.userservice.models.GetUsersResponse;
import com.tim9.userservice.repositories.UserRepository;
import com.tim9.userservice.services.UserService;

@Endpoint
public class UserEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com";
	
	private UserService userService;
	private UserRepository userRepository;

	public UserEndpoint(UserService userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
	@ResponsePayload
	public GetUsersResponse getUsers(@RequestPayload GetUsersRequest request) {
		GetUsersResponse response = new GetUsersResponse();
		response.setUsers(userService.findUsersByAccommodationId(request.getAccommodationId()));
		return response;
	}
}
