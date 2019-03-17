package com.tim9.pkiapi.role.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.role.model.Role;
import com.tim9.pkiapi.role.repository.RoleRepository;

@Component
public class RoleDTOConverter {

	@Autowired
	RoleRepository roleRepository;
	
	public RoleDTO convertToDTO ( Role role ) {
		
		RoleDTO dto = new RoleDTO();
		
		dto.setId(role.getId());
		dto.setName(role.getName());
		
		return dto;
		
	}
	
	public Role convertFromDTO ( RoleDTO dto ) {
		
		Optional<Role> role = roleRepository.findById(dto.getId());
		
		if(role.isPresent()) {
			
			return role.get();
			
		}
		
		Role newRole = new Role();
		
		newRole.setId(dto.getId());
		newRole.setName(dto.getName());
		
		return newRole;
		
	}
}
