package com.tim9.userservice.services;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tim9.userservice.dtoConverters.DTOAdminConverter;
import com.tim9.userservice.models.Admin;
import com.tim9.userservice.models.Agent;
import com.tim9.userservice.models.User;
import com.tim9.userservice.repositories.AdminRepository;
import com.tim9.userservice.repositories.AgentRepository;
import com.tim9.userservice.repositories.UserRepository;
import com.tim9.userserviceClient.dtos.AdminDTO;
import com.tim9.userserviceClient.dtos.AgentDTO;

@Service
public class AdminService {
	
	private final AdminRepository adminRepository;
	private AgentRepository agentRepository;
	private UserRepository userRepository;
	
	private final DTOAdminConverter dtoAdminConverter;
	
	private JavaMailSender javaMailSender;
	
	//Class which loads information from application.properties file.
	private Environment env;
	
	public AdminService(final AdminRepository adminRepository, final DTOAdminConverter dtoAdminConverter,
			JavaMailSender javaMailSender,Environment env,
			AgentRepository agentRepository, UserRepository userRepository) {
		this.adminRepository = adminRepository;
		this.dtoAdminConverter = dtoAdminConverter;
		this.javaMailSender = javaMailSender;
		this.env = env;
		this.agentRepository = agentRepository;
		this.userRepository = userRepository;
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
		//	adminForChange.get().setEmail(admin.getEmail());
			adminForChange.get().setRole(admin.getRole());
	
			adminRepository.save(adminForChange.get());
			
			admin.setId(adminForChange.get().getId());
					
			return admin;		
		}
		
		return new AdminDTO();
	}
	
	public AdminDTO save(AdminDTO admin){
		
		//checking if there is already user or admin or agent with same email adress
		Optional<Admin> foundAdmin = adminRepository.findByEmail(admin.getEmail());
		Optional<Agent> foundAgent = agentRepository.findByEmail(admin.getEmail());
		Optional<User> foundUser = userRepository.findByEmail(admin.getEmail());
		
		if(foundAdmin.isPresent() || foundAgent.isPresent() || foundUser.isPresent()) {
			return new AdminDTO();
		}
		
		admin.setId(-1l);
		
		Admin a = dtoAdminConverter.converFromDTO(admin);
		
		String password = createRandomCode(10, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		
		a.setPassword(password);
		a.setRole("ADMIN");
		adminRepository.save(a);
		
		sendEmail(a);

		
		admin.setId(a.getId());
		
		return admin;
	}
	
	public Boolean changePassword(long id, AdminDTO admin, String email){
		
		Optional<Admin> adminForChange = adminRepository.findById(id);
		
		if(adminForChange.isPresent() && admin != null) {
					
				if(!adminForChange.get().getEmail().equals(email)) {
				
				return false;
			
			}
			
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
	
	@Async
	public void sendEmail(Admin savedUser) {
		
		//now we are sending activation link to the email address of registered user
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(savedUser.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Activation link for MEGATRAVEL [ADMIN]");
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
