package com.tim9.agentapp.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.user.dto.AgentDTO;
import com.tim9.agentapp.user.dto.UpdatePasswordDTO;
import com.tim9.agentapp.user.model.AgentLocal;
import com.tim9.agentapp.user.repository.AgentRepository;
import com.tim9.agentapp.user.soapclient.AgentClient;
import com.tim9.agentapp.user.utils.dtoConverter.DTOAgentConverter;
import com.tim9.agentapp.user.wsdl.GetAgentResponse;
import com.tim9.agentapp.user.wsdl.UpdateAgentPasswordResponse;
import com.tim9.agentapp.user.wsdl.UpdateAgentResponse;

@Service
public class AgentService {
	private final static Logger logger = LoggerFactory.getLogger(AgentService.class);
	private final AgentRepository agentRepository;
	
	private final DTOAgentConverter dtoAgentConverter;
	
	private final AgentClient agentClient;
	
	public AgentService(final AgentRepository agentRepository, final DTOAgentConverter dtoAgentConverter, final AgentClient agentClient) {
		this.agentRepository = agentRepository;
		this.dtoAgentConverter = dtoAgentConverter;
		this.agentClient = agentClient;
	}
	
	public List<AgentDTO> findAll(){
		
		Optional< List<AgentLocal> > agents = Optional.of (agentRepository.findAll());
		
		ArrayList<AgentDTO> dtoAgents = new ArrayList<AgentDTO>();
		
		if (agents.isPresent()) {
			
			for (AgentLocal a : agents.get()) {
				
				dtoAgents.add(dtoAgentConverter.convertToDTO(a));			
			}
			
			return dtoAgents;			
		}
			
		return Collections.emptyList();
	}
	
	public AgentDTO findByEmail(String email){
		
		Optional<AgentLocal> agent = agentRepository.findByEmail(email);
		
		if (agent.isPresent()) {
			
			return dtoAgentConverter.convertToDTO(agent.get());	
		}
		
		return new AgentDTO();
	}
	
	public AgentDTO update(long id, AgentDTO agent){
		
//		Optional<AgentLocal> agentForChange = agentRepository.findById(id);
//		
//		if(agentForChange.isPresent() && agent != null) {
//										
//			agentForChange.get().setFirstName(agent.getFirstName());
//			agentForChange.get().setLastName(agent.getLastName());
//			agentForChange.get().setEmail(agent.getEmail());
////			agentForChange.get().setPassword(agent.getPassword());
////			agentForChange.get().setActivated(agent.getActivated());
//			agentForChange.get().setBusinessRegistrationNumber(agent.getBusinessRegistrationNumber());
////			agentForChange.get().setRole(agent.getRole());
//	
//			agentRepository.save(agentForChange.get());
//			
//			agent.setId(agentForChange.get().getId());
//					
//			return agent;		
//		}
//		
//		return new AgentDTO();
		
		Optional<AgentLocal> agentForChange = agentRepository.findById(id);
		
		if(agentForChange.isPresent() && agent != null) {
			
			UpdateAgentResponse response = agentClient.updateAgent(dtoAgentConverter.convertFromDTOToWsdl(agent));
			
			if(response.getAgent().getId() != null) {
				
				agentForChange.get().setFirstName(agent.getFirstName());
				agentForChange.get().setLastName(agent.getLastName());
				agentForChange.get().setBusinessRegistrationNumber(agent.getBusinessRegistrationNumber());

				agentRepository.save(agentForChange.get());
				
				return agent;
			}
		}
		
		return new AgentDTO();
		
	}
	
	public AgentDTO save(AgentDTO agent){
		
//		agent.setId(-1l);
		
		AgentLocal a = dtoAgentConverter.convertFromDTO(agent);
		a = agentRepository.save(a);
		
		agent.setId(a.getId());
		
		return agent;
	}
	
	public Boolean changePassword(UpdatePasswordDTO password){
		
		Optional<AgentLocal> agentForChange = agentRepository.findByEmail(password.getEmail());
		
		if(agentForChange.isPresent()) {
										
			if(!agentForChange.get().getPassword().equals(password.getOldPassword())) {
				
				return false;
			
			}
			
			UpdateAgentPasswordResponse response = agentClient.updatePassword(password.getEmail(), password.getOldPassword(), password.getNewPassword());
					
			if(response.isSuccess()) {
				
				agentForChange.get().setPassword(password.getNewPassword());
				agentRepository.save(agentForChange.get());
				return true;			
			}		
		}
		
		return false;
	}
	
	public AgentDTO delete(long id){
		
		Optional<AgentLocal> agentToDelete = agentRepository.findByLocalId(id);
		
		if(agentToDelete.isPresent()) {
			
			agentRepository.deleteByLocalId(id);
			
			return dtoAgentConverter.convertToDTO(agentToDelete.get());		
		}
		
		return new AgentDTO();
	}
	
	public boolean syncDatabase(String email){
		
		GetAgentResponse getAgentResponse = agentClient.getAgent(email);
		
		Optional<AgentLocal> agentForChange = agentRepository.findById(getAgentResponse.getAgent().getId().longValue());
//		logger.error(getAgentResponse.getAgent().getId()+"");
// 		obraditi slucaj inicijalizacije
		if(agentForChange.isPresent() && getAgentResponse != null) {
		
			agentForChange.get().setFirstName(getAgentResponse.getAgent().getFirstName());
			agentForChange.get().setLastName(getAgentResponse.getAgent().getLastName());
			agentForChange.get().setEmail(getAgentResponse.getAgent().getEmail());
			agentForChange.get().setPassword(getAgentResponse.getAgent().getPassword());
			agentForChange.get().setActivated(getAgentResponse.getAgent().isActivated());
			agentForChange.get().setBusinessRegistrationNumber(getAgentResponse.getAgent().getBusinessRegistrationNumber());
			agentForChange.get().setRole(getAgentResponse.getAgent().getRole());
	
			agentRepository.save(agentForChange.get());
					
			return true;	
			
		} else {
			return false;
		}
	}
	
}
