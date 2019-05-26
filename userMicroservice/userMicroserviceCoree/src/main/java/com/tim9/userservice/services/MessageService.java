package com.tim9.userservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.userservice.dtoConverters.DTOMessageConverter;
import com.tim9.userservice.models.Message;
import com.tim9.userservice.repositories.MessageRepository;

import dtos.MessageDTO;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private DTOMessageConverter dtoMessageConverter;
	
	public MessageDTO findById(long id){
		
		Optional<Message> message = messageRepository.findById(id);
		
		if (message.isPresent()) {
			
			return dtoMessageConverter.convertToDTO(message.get());	
		}
		
		return new MessageDTO();		
	}
	
	public MessageDTO save(MessageDTO message){
		
		message.setMessageId(-1l);
		
		Message m = messageRepository.save(dtoMessageConverter.converFromDTO(message));
		
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
