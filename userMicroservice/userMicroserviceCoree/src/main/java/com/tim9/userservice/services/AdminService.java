package com.tim9.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.userservice.dtoConverters.DTOAdminConverter;
import com.tim9.userservice.models.Admin;
import com.tim9.userservice.repositories.AdminRepository;

import dtos.AdminDTO;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	
	private final DTOAdminConverter dtoAdminConverter;
	
	public AdminService(final AdminRepository adminRepository, final DTOAdminConverter dtoAdminConverter) {
		this.adminRepository = adminRepository;
		this.dtoAdminConverter = dtoAdminConverter;
	}
	
	public List<AdminDTO> findAll(){
		
		Optional< List<Admin> > admins = Optional.of (adminRepository.findAll());
		
		ArrayList<AdminDTO> dtoAdmins = new ArrayList<AdminDTO>();
		
		if (admins.isPresent()) {
			
			for (Admin a : admins.get()) {
				
				dtoAdmins.add(dtoAdminConverter.convertToDTO(a));			
			}
			
			return dtoAdmins;			
		}
			
		return Collections.emptyList();
	}
	
	public AdminDTO findById(long id){
		
		Optional<Admin> admin = adminRepository.findById(id);
			
		if (admin.isPresent()) {
			
			return dtoAdminConverter.convertToDTO(admin.get());	
		}
		
		return new AdminDTO();			
	}
	
	public AdminDTO update(long id, AdminDTO admin){
		
		Optional<Admin> adminForChange = adminRepository.findById(id);
		
		if(adminForChange.isPresent() && admin != null) {
										
			adminForChange.get().setFirstName(admin.getFirstName());
			adminForChange.get().setLastName(admin.getLastName());
			adminForChange.get().setEmail(admin.getEmail());
			adminForChange.get().setRole(admin.getRole());
	
			adminRepository.save(adminForChange.get());
			
			admin.setId(adminForChange.get().getId());
					
			return admin;		
		}
		
		return new AdminDTO();
	}
	
	public AdminDTO save(AdminDTO admin){
		
		admin.setId(-1l);
		
		Admin a = adminRepository.save(dtoAdminConverter.converFromDTO(admin));
		
		admin.setId(a.getId());
		
		return admin;
	}
	
	public Boolean changePassword(long id, AdminDTO admin){
		
		Optional<Admin> adminForChange = adminRepository.findById(id);
		
		if(adminForChange.isPresent() && admin != null) {
										
			adminForChange.get().setPassword(admin.getPassword());

			adminRepository.save(adminForChange.get());
					
			return true;		
		}
		
		return false;
	}
	
	
	public AdminDTO delete(long id){
		
		Optional<Admin> adminToDelete = adminRepository.findById(id);
		
		if(adminToDelete.isPresent()) {
			
			adminRepository.deleteById(id);
			
			return dtoAdminConverter.convertToDTO(adminToDelete.get());		
		}
		
		return new AdminDTO();
	}
	
}
