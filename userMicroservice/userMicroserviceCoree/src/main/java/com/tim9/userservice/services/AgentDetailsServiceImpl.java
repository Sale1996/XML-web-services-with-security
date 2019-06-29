package com.tim9.userservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tim9.userservice.models.Agent;
import com.tim9.userservice.repositories.AgentRepository;

@Service
public class AgentDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AgentRepository agentRepostory;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Agent> agent = this.agentRepostory.findByEmail(email);
		List<GrantedAuthority> grantedAuthorities;

		if(agent.isPresent() && agent.get().getActivated()) {
			if(agent.get().getRole().equals("AGENT")) {
				grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + agent.get().getRole() + ", " + "CREATE_ACCOMMODATION, UPDATE_ACCOMMODATION, DELETE_ACCOMMODATION, READ_RESERVATION, UPDATE_RESERVATION, DELETE_RESERVATION");
				return new User(agent.get().getEmail(), encoder.encode(agent.get().getPassword()), grantedAuthorities);
			}
		}

		throw new UsernameNotFoundException("Email: " + email + " not found");
	}
}