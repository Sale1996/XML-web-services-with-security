package com.tim9.accommodationservice.utils;

import org.springframework.stereotype.Component;

import com.tim9.userserviceClient.dtos.AgentDTO;
import com.tim9.userserviceClient.feignClients.AgentClient;

import feign.FeignException;

@Component
public class DatasFromUserMicroservice {

	private AgentClient agentClient;

	
	public DatasFromUserMicroservice(AgentClient agentClient) {
  
		this.agentClient = agentClient;
	}
	

	public AgentDTO getAgentById(Long id) {

		AgentDTO agent = null;
		try {
			agent = agentClient.getAgentById(id);
		} catch (FeignException e) {
			return new AgentDTO();

		}
		return agent;
	}

}
