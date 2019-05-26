package com.tim9.userservice.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.userservice.models.Agent;
import com.tim9.userservice.repositories.AgentRepository;

import dtos.AgentDTO;

@Component
public class DTOAgentConverter {

	@Autowired
	private AgentRepository agentRepository;
	
	public AgentDTO convertToDTO(Agent agent) {
		
		AgentDTO dto = new AgentDTO();
		
		dto.setId(agent.getId());
		dto.setFirstName(agent.getFirstName());
		dto.setLastName(agent.getLastName());
		dto.setEmail(agent.getEmail());
		dto.setBusinessRegistrationNumber(agent.getBusinessRegistrationNumber());
		dto.setActivated(agent.getActivated());
		dto.setRole(agent.getRole());
		
		return dto;
	}
	
	public Agent convertFromDTO(AgentDTO dto) {
		
		Optional<Agent> agent = agentRepository.findById(dto.getId());
		
		if(agent.isPresent()) {
			
			return agent.get();
		}
		
		Agent a = new Agent();
		
		a.setId(dto.getId());
		a.setFirstName(dto.getFirstName());
		a.setLastName(dto.getLastName());
		a.setEmail(dto.getEmail());
		a.setBusinessRegistrationNumber(dto.getBusinessRegistrationNumber());
		a.setActivated(dto.getActivated());
		a.setPassword(dto.getPassword());
		a.setRole(dto.getRole());
		
		return a;
	}
	
}
