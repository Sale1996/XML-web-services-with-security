package com.tim9.userservice.services;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.impl.cookie.BrowserCompatSpecFactory.SecurityLevel;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.netflix.config.DeploymentContext.ContextKey;
import com.tim9.userservice.dtoConverters.DTOAgentConverter;
import com.tim9.userservice.models.Agent;
import com.tim9.userservice.repositories.AgentRepository;
import com.tim9.userserviceClient.dtos.AgentDTO;

@Service
public class AgentService {
	
	private final AgentRepository agentRepository;
	
	private final DTOAgentConverter dtoAgentConverter;
	
	
	private JavaMailSender javaMailSender;
	
	//Class which loads information from application.properties file.
	private Environment env;
	
	public AgentService(final AgentRepository agentRepository, final DTOAgentConverter dtoAgentConverter,
			JavaMailSender javaMailSender,Environment env ) {
		this.agentRepository = agentRepository;
		this.dtoAgentConverter = dtoAgentConverter;
		this.javaMailSender = javaMailSender;
		this.env = env;
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
	
//	public AgentDTO findByEmail(String email){
//		
//		Optional<Agent> agent = agentRepository.findByEmail(email);
//		
//		if (agent.isPresent()) {
//			
//			return dtoAgentConverter.convertToDTO(agent.get());	
//		}
//		
//		return new AgentDTO();		
//	}
	
	
	public Agent findByIdNOTDTO(long id){
		
		Optional<Agent> agent = agentRepository.findById(id);
		
			return agent.get();		
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
		
		String password = createRandomCode(10, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		
		a.setPassword(password);
		
		a = agentRepository.save(a);
		
		agent.setId(a.getId());
		
		sendEmail(a);
		
		return agent;
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

//	public Boolean changePassword(long id, AgentDTO agent, String email) {
//		
//		Optional<Agent> agentForChange = agentRepository.findById(id);
//		
//		if(agentForChange.isPresent() && agent != null) {
//			
//			if(!agentForChange.get().getEmail().equals(email)) {
//				
//				return false;
//			
//			}
//										
//			agentForChange.get().setPassword(agent.getPassword());
//
//			agentRepository.save(agentForChange.get());
//					
//			return true;		
//		}
//		
//		return false;
//	}
	
	public Boolean changePassword(String email, String oldPassword, String newPassword) {
		
		Optional<Agent> agentForChange = agentRepository.findByEmail(email);
		
		if(agentForChange.isPresent()) {
			
			if(!agentForChange.get().getPassword().equals(oldPassword)) {
				
				return false;
			
			}
										
			agentForChange.get().setPassword(newPassword);

			agentRepository.save(agentForChange.get());
					
			return true;		
		}
		
		return false;
	}
	
	@Async
	public void sendEmail(Agent savedUser) {
		
		//now we are sending activation link to the email address of registered user
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(savedUser.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Activation link for MEGATRAVEL [AGENT]");
		mail.setText("Hello " + savedUser.getFirstName() + ",\n\n Thank you for registration in our website. \n "
				+ "Your credentials are: \n \n Username: " + savedUser.getEmail() + " \n Password:" + savedUser.getPassword() );
		javaMailSender.send(mail);
		
		
	}
	
	
	public static String createRandomCode(int codeLength, String id) {
	    return new SecureRandom()
	            .ints(codeLength, 0, id.length())
	            .mapToObj(id::charAt)
	            .map(Object::toString)
	            .collect(Collectors.joining());
	}
	
}
