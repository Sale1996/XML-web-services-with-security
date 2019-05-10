package com.project.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dtoConverters.DTOAdminConverter;
import com.project.userservice.dtos.AdminDTO;
import com.project.userservice.models.Admin;
import com.project.userservice.repositories.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private DTOAdminConverter dtoAdminConverter;
	
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
			adminForChange.get().setPassword(admin.getPassword());
	
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
	
	public AdminDTO delete(long id){
		
		Optional<Admin> adminToDelete = adminRepository.findById(id);
		
		if(adminToDelete.isPresent()) {
			
			adminRepository.deleteById(id);
			
			return dtoAdminConverter.convertToDTO(adminToDelete.get());		
		}
		
		return new AdminDTO();
	}
	
}
