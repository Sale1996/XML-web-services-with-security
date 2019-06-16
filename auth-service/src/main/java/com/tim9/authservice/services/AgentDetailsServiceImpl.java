package com.tim9.authservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tim9.authservice.utils.DatasFromUserMicroservice;
import com.tim9.userserviceClient.dtos.AgentDTO;

@Service
public class AgentDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private DatasFromUserMicroservice datasFromUserMicroservice;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		
		AgentDTO agent = this.datasFromUserMicroservice.getAgentByEmail(email);
		
		List<GrantedAuthority> grantedAuthorities;
		if(agent.getEmail().equals(email)) {
			
			if(agent.getRole().equals("AGENT")) {
				// Remember that Spring needs roles to be in this format: "ROLE_" + userRole (i.e. "ROLE_ADMIN")
				// So, we need to set it to that format, so we can verify and compare roles (i.e. hasRole("ADMIN")).
//				List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//		                	.commaSeparatedStringToAuthorityList("ROLE_" + appUser.getRole());
				grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + agent.getRole() + ", " + "CREATE_ACCOMMODATION, UPDATE_ACCOMMODATION, DELETE_ACCOMMODATION, READ_RESERVATION, UPDATE_RESERVATION, DELETE_RESERVATION");
				
				// The "User" class is provided by Spring and represents a model class for user to be returned by UserDetailsService
				// And used by auth manager to verify and check user authentication.
				return new User(agent.getEmail(), encoder.encode("agent"), grantedAuthorities);
			} 
//			else if(agent.getRole().equals("ADMIN")) {
//				grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + agent.getRole() + ", " + "DELETE_ACCOMMODATION");
//				return new User(agent.getEmail(), agent.getPassword(), grantedAuthorities);
//			} else  {
//				grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + agent.getRole() + ", " + "CREATE_RESERVATION, READ_RESERVATION, UPDATE_RESERVATION, REVOKE_RESERVATON");
//				return new User(agent.getEmail(), agent.getPassword(), grantedAuthorities);
//			}
		}
		
		// If user not found. Throw this exception.
		throw new UsernameNotFoundException("Email: " + email + " not found");
	}
	
}