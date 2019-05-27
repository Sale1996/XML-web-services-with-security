package com.tim9.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.userservice.dtoConverters.DTOAgentConverter;
import com.tim9.userservice.models.Agent;
import com.tim9.userservice.repositories.AgentRepository;

import dtos.AgentDTO;

@Service
public class AgentService {
	
	private final AgentRepository agentRepository;
	
	private final DTOAgentConverter dtoAgentConverter;
	
	public AgentService(final AgentRepository agentRepository, final DTOAgentConverter dtoAgentConverter) {
		this.agentRepository = agentRepository;
		this.dtoAgentConverter = dtoAgentConverter;
	}
	
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
	
	public Boolean changePassword(long id, AgentDTO agent){
		
		Optional<Agent> agentForChange = agentRepository.findById(id);
		
		if(agentForChange.isPresent() && agent != null) {
										
			agentForChange.get().setPassword(agent.getPassword());

			agentRepository.save(agentForChange.get());
					
			return true;		
		}
		
		return false;
	}
	
	public Boolean changeStatus(long id, AgentDTO agent){
		
		Optional<Agent> agentForChange = agentRepository.findById(id);
		
		if(agentForChange.isPresent() && agent != null) {
										
			agentForChange.get().setActivated(agent.getActivated());

			agentRepository.save(agentForChange.get());
					
			return true;		
		}
		
		return false;
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
