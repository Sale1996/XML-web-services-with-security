package com.tim9.agentapp.user.utils.dtoConverter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.user.dto.AgentDTO;
import com.tim9.agentapp.user.model.AgentLocal;
import com.tim9.agentapp.user.repository.AgentRepository;
import com.tim9.agentapp.user.wsdl.Agent;

@Component
public class DTOAgentConverter {

	@Autowired
	private AgentRepository agentRepository;
	
	public AgentDTO convertToDTO(AgentLocal agent) {
		
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
	
	public AgentDTO convertToDTOFromClient(Agent agent) {
		
		AgentDTO dto = new AgentDTO();
		
		dto.setId(agent.getId());
		dto.setFirstName(agent.getFirstName());
		dto.setLastName(agent.getLastName());
		dto.setEmail(agent.getEmail());
		dto.setBusinessRegistrationNumber(agent.getBusinessRegistrationNumber());
		dto.setActivated(agent.isActivated());
		dto.setRole(agent.getRole());
		
		return dto;
	}
	
	public AgentLocal convertFromDTO(AgentDTO dto) {
		
		Optional<AgentLocal> agent = agentRepository.findById(dto.getId());
		
		if(agent.isPresent()) {
			
			return agent.get();
		}
		
		AgentLocal a = new AgentLocal();
		
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
	
	public Agent convertFromDTOToWsdl(AgentDTO dto) {
		
		Agent a = new Agent();
		
		a.setId(dto.getId());
		a.setFirstName(dto.getFirstName());
		a.setLastName(dto.getLastName());
		a.setEmail(dto.getEmail());
		a.setBusinessRegistrationNumber(dto.getBusinessRegistrationNumber());
		
		return a;
	}
	
}
