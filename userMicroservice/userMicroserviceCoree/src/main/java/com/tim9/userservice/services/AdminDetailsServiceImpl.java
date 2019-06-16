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

import com.tim9.userservice.models.Admin;
import com.tim9.userservice.repositories.AdminRepository;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Admin> a = this.adminRepository.findByEmail(email);
		List<GrantedAuthority> grantedAuthorities;

		if(a.isPresent()) {
			if(a.get().getRole().equals("ADMIN")) {
				grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_" + a.get().getRole() + ", " + "CREATE_TYPE, UPDATE_TYPE, DELETE_TYPE, CREATE_CATEGORY, UPDATE_CATEGORY, DELETE_CATEGORY, CREATE_CITY, UPDATE_CITY, DELETE_CITY, CREATE_EXTRA_FIELD, UPDATE_EXTRA_FIELD, DELETE_EXTRA_FIELD");
				return new User(a.get().getEmail(), encoder.encode(a.get().getPassword()), grantedAuthorities);
			}
		}

		throw new UsernameNotFoundException("Email: " + email + " not found");
	}
}