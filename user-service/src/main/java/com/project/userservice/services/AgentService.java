package com.project.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dtoConverters.DTOAgentConverter;
import com.project.userservice.dtos.AgentDTO;
import com.project.userservice.models.Agent;
import com.project.userservice.repositories.AgentRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	@Autowired
	private DTOAgentConverter dtoAgentConverter;
	
	public List<AgentDTO> findAll(){
		
		Optional< List<Agent> > agents = Optional.of (agentRepository.findAll());
		
		ArrayList<AgentDTO> dtoAgents = new ArrayList<AgentDTO>();
		
		if (agents.isPresent()) {
			
			for (Agent a : agents.get()) {
				
				dtoAgents.add(dtoAgentConverter.convertToDTO(a));			
			}
			
			return dtoAgents;			
		}
			
		return Collections.emptyList();
	}
	
	public AgentDTO findById(long id){
		
		Optional<Agent> agent = agentRepository.findById(id);
		
		if (agent.isPresent()) {
			
			return dtoAgentConverter.convertToDTO(agent.get());	
		}
		
		return new AgentDTO();		
	}
	
	public AgentDTO update(long id, AgentDTO agent){
		
		Optional<Agent> agentForChange = agentRepository.findById(id);
		
		if(agentForChange.isPresent() && agent != null) {
										
			agentForChange.get().setFirstName(agent.getFirstName());
			agentForChange.get().setLastName(agent.getLastName());
			agentForChange.get().setEmail(agent.getEmail());
			agentForChange.get().setPassword(agent.getPassword());
			agentForChange.get().setActivated(agent.getActivated());
			agentForChange.get().setBusinessRegistrationNumber(agent.getBusinessRegistrationNumber());
			agentForChange.get().setRole(agent.getRole());
	
			agentRepository.save(agentForChange.get());
			
			agent.setId(agentForChange.get().getId());
					
			return agent;		
		}
		
		return new AgentDTO();
	}
	
	public AgentDTO save(AgentDTO agent){
		
		agent.setId(-1l);
		
		Agent a = agentRepository.save(dtoAgentConverter.convertFromDTO(agent));
		
		agent.setId(a.getId());
		
		return agent;
	}
	
	public AgentDTO delete(long id){
		
		Optional<Agent> agentToDelete = agentRepository.findById(id);
		
		if(agentToDelete.isPresent()) {
			
			agentRepository.deleteById(id);
			
			return dtoAgentConverter.convertToDTO(agentToDelete.get());		
		}
		
		return new AgentDTO();
	}
	
}
