package com.tim9.userservice.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.reservationserviceClient.dtos.ReservationDTO;
import com.tim9.userservice.dtoConverters.DTOMessageConverter;
import com.tim9.userservice.models.Message;
import com.tim9.userservice.models.User;
import com.tim9.userservice.repositories.MessageRepository;
import com.tim9.userservice.repositories.UserRepository;
import com.tim9.userservice.utils.DatasFromReservationMicroservice;
import com.tim9.userserviceClient.dtos.MessageDTO;

@Service
public class MessageService {
	
	private final MessageRepository messageRepository;
	private UserRepository userRepository;
	
	private final DTOMessageConverter dtoMessageConverter;
	
	private DatasFromReservationMicroservice reservationMicroservice;
	
	
	public MessageService(final MessageRepository messageRepository, final DTOMessageConverter dtoMessageConverter,
			DatasFromReservationMicroservice reservationMicroservice,final UserRepository userRepository) {
		
		this.messageRepository = messageRepository;
		this.dtoMessageConverter = dtoMessageConverter;
		this.reservationMicroservice = reservationMicroservice;
		this.userRepository = userRepository;
	
	}
	
	public MessageDTO findById(long id){
		
		Optional<Message> message = messageRepository.findById(id);
		
		if (message.isPresent()) {
			
			return dtoMessageConverter.convertToDTO(message.get());	
		}
		
		return new MessageDTO();		
	}
	
	public MessageDTO save(MessageDTO message, String email){
		
		/*
		 * Proveravamo prvo da li je ulogovan user , onaj user koji zeli poslati poruku, takodje
		 * proveriti da li je user sa rezervacije poklapa sa userom iz poruke od te rezervacije
		 */
		
		ReservationDTO reservation = reservationMicroservice.getReservationById(message.getReservationId());
		
		Optional<User> user = userRepository.findByEmail(email);
		
		if(!user.isPresent()) {
			return new MessageDTO();
		}
		
		if(user.get().getId() != message.getUserId() || message.getUserId() != reservation.getClient()) {
			return new MessageDTO();
		}
		
		message.setMessageId(-1l);
		
		Message m = dtoMessageConverter.converFromDTO(message);
		m.setLastUpdated(LocalDateTime.now());
				
		m = messageRepository.save(m);
		
		message.setMessageId(m.getMessageId());
		
		return message;
	}
	
	public MessageDTO delete(long id){
		
		Optional<Message> messageToDelete = messageRepository.findById(id);
		
		if(messageToDelete.isPresent()) {
			
			messageRepository.deleteById(id);
			
			return dtoMessageConverter.convertToDTO(messageToDelete.get());		
		}
		
		return new MessageDTO();
	}
	
	
	

}
