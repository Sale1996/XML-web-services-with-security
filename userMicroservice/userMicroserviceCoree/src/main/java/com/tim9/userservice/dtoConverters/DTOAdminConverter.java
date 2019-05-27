package com.tim9.userservice.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.userservice.models.Admin;
import com.tim9.userservice.repositories.AdminRepository;

import dtos.AdminDTO;


@Component
public class DTOAdminConverter {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public AdminDTO convertToDTO(Admin admin) {
		
		AdminDTO dto = new AdminDTO();
		
		dto.setId(admin.getId());
		dto.setFirstName(admin.getFirstName());
		dto.setLastName(admin.getLastName());
		dto.setEmail(admin.getEmail());
		dto.setRole(admin.getRole());
		
		return dto;
	}
	
	public Admin converFromDTO(AdminDTO dto) {
		
		Optional<Admin> admin = adminRepository.findById(dto.getId());
		
		if(admin.isPresent()) {
			
			return admin.get();
		}
		
		Admin a = new Admin();
		
		a.setId(dto.getId());
		a.setFirstName(dto.getFirstName());
		a.setLastName(dto.getLastName());
		a.setEmail(dto.getEmail());
		a.setPassword(dto.getPassword());
		a.setRole(dto.getRole());
			
		return a;
		
	}
	
}
