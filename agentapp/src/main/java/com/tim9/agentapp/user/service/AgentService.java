package com.tim9.agentapp.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.user.dto.AgentDTO;
import com.tim9.agentapp.user.dto.UserDTO;
import com.tim9.agentapp.user.model.Agent;
import com.tim9.agentapp.user.repository.AgentRepository;
import com.tim9.agentapp.user.soapclient.UserClient;
import com.tim9.agentapp.user.utils.dtoConverter.DTOAgentConverter;
import com.tim9.agentapp.user.wsdl.GetAgentResponse;

@Service
public class AgentService {
	
	private final AgentRepository agentRepository;
	
	private final DTOAgentConverter dtoAgentConverter;
	
	private final UserClient userClient;
	
	public AgentService(final AgentRepository agentRepository, final DTOAgentConverter dtoAgentConverter, final UserClient userClient) {
		this.agentRepository = agentRepository;
		this.dtoAgentConverter = dtoAgentConverter;
		this.userClient = userClient;
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
		
		Agent a = dtoAgentConverter.convertFromDTO(agent);
		a = agentRepository.save(a);
		
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
	
	public AgentDTO delete(long id){
		
		Optional<Agent> agentToDelete = agentRepository.findById(id);
		
		if(agentToDelete.isPresent()) {
			
			agentRepository.deleteById(id);
			
			return dtoAgentConverter.convertToDTO(agentToDelete.get());		
		}
		
		return new AgentDTO();
	}
	
	public AgentDTO syncDatabase(){
		
		GetAgentResponse getAgentResponse = userClient.getAgent("4");
		
		return dtoAgentConverter.convertToDTOFromClient(getAgentResponse.getAgent());
	}
	
}
