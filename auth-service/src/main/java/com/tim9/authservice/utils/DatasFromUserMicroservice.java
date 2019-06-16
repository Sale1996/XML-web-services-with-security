package com.tim9.authservice.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.userserviceClient.dtos.AgentDTO;
import com.tim9.userserviceClient.feignClients.AgentClient;
import com.tim9.userserviceClient.feignClients.UserClient;

import feign.FeignException;

@Component
public class DatasFromUserMicroservice {

	@Autowired
	private AgentClient agentClient;
	
	public AgentDTO getAgentByEmail(String username) {

		AgentDTO agent = null;
		try {
			agent = agentClient.getAgentByEmail(username);
		} catch (FeignException e) {
			return new AgentDTO();

		}
		return agent;
	}
}
