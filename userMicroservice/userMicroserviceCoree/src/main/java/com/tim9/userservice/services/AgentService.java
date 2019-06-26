package com.tim9.userservice.services;

import java.security.SecureRandom;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.tim9.userservice.dtoConverters.DTOAgentConverter;
import com.tim9.userservice.models.Admin;
import com.tim9.userservice.models.Agent;
import com.tim9.userservice.models.User;
import com.tim9.userservice.repositories.AdminRepository;
import com.tim9.userservice.repositories.AgentRepository;
import com.tim9.userservice.repositories.UserRepository;
import com.tim9.userserviceClient.dtos.AgentDTO;
import com.tim9.userserviceClient.dtos.UserDTO;
import com.tim9.userserviceClient.jwt.JwtConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AgentService {
	
	private final AgentRepository agentRepository;
	private AdminRepository adminRepository;
	private UserRepository userRepository;
	private final AgentDetailsServiceImpl agentDetailsServiceImpl;
	private AuthenticationManager authManager;
	private final JwtConfig jwtConfig;
	
	private final DTOAgentConverter dtoAgentConverter;
	
	
	private JavaMailSender javaMailSender;
	
	//Class which loads information from application.properties file.
	private Environment env;
	
	public AgentService(final AgentRepository agentRepository, final DTOAgentConverter dtoAgentConverter,
			JavaMailSender javaMailSender,Environment env,
			AdminRepository adminRepository, UserRepository userRepository,
			AgentDetailsServiceImpl agentDetailsServiceImpl,
			AuthenticationManager authManager,
			JwtConfig jwtConfig) {
		this.agentRepository = agentRepository;
		this.dtoAgentConverter = dtoAgentConverter;
		this.javaMailSender = javaMailSender;
		this.env = env;
		this.adminRepository = adminRepository;
		this.userRepository = userRepository;
		this.agentDetailsServiceImpl = agentDetailsServiceImpl;
		this.authManager = authManager;
		this.jwtConfig = jwtConfig;
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
	
	
	public Agent findByEmailNOTDTO(String email){
		
		Optional<Agent> agent = agentRepository.findByEmail(email);
		
			return agent.get();		
	}
	
	
	
	
	public Agent update(long id, Agent agent){
		
		Optional<Agent> agentForChange = agentRepository.findById(id);
		
		if(agentForChange.isPresent() && agent != null) {
										
			agentForChange.get().setFirstName(agent.getFirstName());
			agentForChange.get().setLastName(agent.getLastName());
//			agentForChange.get().setEmail(agent.getEmail());
//			agentForChange.get().setPassword(agent.getPassword());
//			agentForChange.get().setActivated(agent.getActivated());
			agentForChange.get().setBusinessRegistrationNumber(agent.getBusinessRegistrationNumber());
//			agentForChange.get().setRole(agent.getRole());
	
			agentRepository.save(agentForChange.get());
			
			agent.setId(agentForChange.get().getId());
					
			return agent;		
		}
		
		return new Agent();
	}
	
	public AgentDTO save(AgentDTO agent){
		
		//checking if there is already user or admin or agent with same email adress
		Optional<Admin> admin = adminRepository.findByEmail(agent.getEmail());
		Optional<Agent> foundAgent = agentRepository.findByEmail(agent.getEmail());
		Optional<User> foundUser = userRepository.findByEmail(agent.getEmail());
		
		if(admin.isPresent() || foundAgent.isPresent() || foundUser.isPresent()) {
			return new AgentDTO();
		}
		
		
		agent.setId(-1l);
		
		agent.setActivated(true);
		
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
	
	public String autoLogin(String username, String password) {
        UserDetails userDetails = agentDetailsServiceImpl.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        authManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
        	
        	
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            
            Long now = System.currentTimeMillis();
            return  Jwts.builder()
            		.setSubject(SecurityContextHolder.getContext().getAuthentication().getName())	
            		// Convert to list of strings. 
            		// This is important because it affects the way we get them back in the Gateway.
            		.claim("authorities", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_AGENT" + ", " + "CREATE_ACCOMMODATION, UPDATE_ACCOMMODATION, DELETE_ACCOMMODATION, READ_RESERVATION, UPDATE_RESERVATION, DELETE_RESERVATION").stream()
            				.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
            		.setIssuedAt(new Date(now))
            		.setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))  // in milliseconds
            		.signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
            		.compact();
        }
        
        return "";
    }
	
}
