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

import com.tim9.userservice.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<com.tim9.userservice.models.User> u = this.userRepository.findByEmail(email);
		List<GrantedAuthority> grantedAuthorities;

		if(u.isPresent() && u.get().getActivated()) {
			if(u.get().getRole().equals("USER")) {
				grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + u.get().getRole() + ", " + "CREATE_RESERVATION, READ_RESERVATION, UPDATE_RESERVATION, REVOKE_RESERVATON");
				return new User(u.get().getEmail(), encoder.encode(u.get().getPassword()), grantedAuthorities);
			}
		}

		throw new UsernameNotFoundException("Email: " + email + " not found");
	}
}