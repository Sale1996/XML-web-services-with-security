package com.tim9.userservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.userservice.dtoConverters.DTOUserConverter;
import com.tim9.userservice.models.Admin;
import com.tim9.userservice.models.Agent;
import com.tim9.userservice.models.User;
import com.tim9.userservice.repositories.AdminRepository;
import com.tim9.userservice.repositories.AgentRepository;
import com.tim9.userservice.repositories.UserRepository;
import com.tim9.userservice.utils.DatasFromReservationMicroservice;
import com.tim9.userserviceClient.dtos.UserDTO;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private AdminRepository adminRepository;
	private AgentRepository agentRepository;
	
	private final DTOUserConverter dtoUserConverter;
	
	private DatasFromReservationMicroservice reservationMicroservice;
	
	public UserService(final UserRepository userRepository, final DTOUserConverter dtoUserConverter, DatasFromReservationMicroservice reservationMicroservice,
			AdminRepository adminRepository, AgentRepository agentRepository) {
		this.userRepository = userRepository;
		this.dtoUserConverter = dtoUserConverter;
		this.reservationMicroservice = reservationMicroservice;
		this.adminRepository = adminRepository;
		this.agentRepository = agentRepository;
	}
	
	public List<UserDTO> findAll(){
		
		Optional<List<User>> users = Optional.of (userRepository.findAll());
		
		ArrayList<UserDTO> dtoUsers = new ArrayList<UserDTO>();
		
		if (users.isPresent()) {
			
			for (User u : users.get()) {
				
				dtoUsers.add(dtoUserConverter.convertToDTO(u));			
			}
			
			return dtoUsers;			
		}
			
		return Collections.emptyList();
	}
	
	public UserDTO findById(long id){
		
		Optional<User> user = userRepository.findById(id);
		
		if (user.isPresent()) {
			
			return dtoUserConverter.convertToDTO(user.get());	
		}
		
		return new UserDTO();	
	}
	
	public UserDTO findByEmail(String email){
		
		Optional<User> user = userRepository.findByEmail(email);
		
		if (user.isPresent()) {
			
			return dtoUserConverter.convertToDTO(user.get());	
		}
		
		return new UserDTO();	
	}
	
	public UserDTO update(long id, UserDTO user){
		
		Optional<User> userForChange = userRepository.findById(id);
		
		if(userForChange.isPresent() && user != null) {
										
			userForChange.get().setFirstName(user.getFirstName());
			userForChange.get().setLastName(user.getLastName());
		//	userForChange.get().setEmail(user.getEmail());
			userForChange.get().setAddress(user.getAddress());
			userForChange.get().setActivated(user.getActivated());
			userForChange.get().setTelephoneNumber(user.getTelephoneNumber());
			userForChange.get().setRole(user.getRole());
	
			userRepository.save(userForChange.get());
			
			user.setId(userForChange.get().getId());
					
			return user;		
		}
		
		return new UserDTO();
	}
	
	public Boolean changePassword(long id, UserDTO user, String email){
		
		Optional<User> userForChange = userRepository.findById(id);
		
		if(userForChange.isPresent() && user != null) {
										
			if(!userForChange.get().getEmail().equals(email)) {
				
				return false;
			
			}
			
			userForChange.get().setPassword(user.getPassword());

			userRepository.save(userForChange.get());
					
			return true;		
		}
		
		return false;
	}
	
	public Boolean changeStatus(long id, UserDTO user){
		
		Optional<User> userForChange = userRepository.findById(id);
		
		if(userForChange.isPresent() && user != null) {
										
			userForChange.get().setActivated(user.getActivated());

			userRepository.save(userForChange.get());
					
			return true;		
		}
		
		return false;
	}
	
	public UserDTO save(UserDTO user){
		
		//checking if there is already user or admin or agent with same email adress
		Optional<Admin> admin = adminRepository.findByEmail(user.getEmail());
		Optional<Agent> agent = agentRepository.findByEmail(user.getEmail());
		Optional<User> foundUser = userRepository.findByEmail(user.getEmail());
		
		if(admin.isPresent() || agent.isPresent() || foundUser.isPresent()) {
			return new UserDTO();
		}
		
		user.setId(-1l);
		
		User u = userRepository.save(dtoUserConverter.convertFromDTO(user));
		
		user.setId(u.getId());
		
		return user;
	}
	
	public UserDTO delete(long id){
		
		Optional<User> userToDelete = userRepository.findById(id);
		
		if(userToDelete.isPresent()) {
			
			userRepository.deleteById(id);
			
			return dtoUserConverter.convertToDTO(userToDelete.get());		
		}
		
		return new UserDTO();
	}

	public List<User> findUsersByAccommodationId(Long accommodationId) {
		
		// prvo pokupi id-jeve svih klijenata date akomodacije
		List<Long> ids = reservationMicroservice.getAccommodationClients(accommodationId);
		
		ids.add(-1l);
		
		// a onda samo izvuci iz baze sve klijente koji se odazivaju na prisutne id-jeve
		Optional<List<User>> users = Optional.of (userRepository.usersByIds(ids));
		
		return users.get();
	}
}
