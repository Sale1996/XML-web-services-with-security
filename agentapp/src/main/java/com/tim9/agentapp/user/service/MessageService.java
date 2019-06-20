package com.tim9.agentapp.user.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.agentapp.user.dto.AgentDTO;
import com.tim9.agentapp.user.dto.MessageDTO;
import com.tim9.agentapp.user.model.Agent;
import com.tim9.agentapp.user.model.Message;
import com.tim9.agentapp.user.repository.MessageRepository;
import com.tim9.agentapp.user.utils.dtoConverter.DTOMessageConverter;

@Service
public class MessageService {
	
	private final MessageRepository messageRepository;
	
	private final DTOMessageConverter dtoMessageConverter;
	
	public MessageService(final MessageRepository messageRepository, final DTOMessageConverter dtoMessageConverter) {
		this.messageRepository = messageRepository;
		this.dtoMessageConverter = dtoMessageConverter;
	}
	
	public MessageDTO findById(long id){
		
		Optional<Message> message = messageRepository.findById(id);
		
		if (message.isPresent()) {
			
			return dtoMessageConverter.convertToDTO(message.get());	
		}
		
		return new MessageDTO();		
	}
	
	public MessageDTO save(MessageDTO message){
		
		message.setMessageId(-1l);
		
		Message m = dtoMessageConverter.converFromDTO(message);
		m.setLastUpdated(LocalDateTime.now());
				
		m = messageRepository.save(m);
		
		message.setMessageId(m.getMessageId());
		
		return message;
	}
	
	public List<MessageDTO> getAllMessagesOrderedByLatest(){
		
		Optional< List<Message> > messages = Optional.of(messageRepository.getAllMessagesOrderedByLatest());
		
		ArrayList<MessageDTO> dtoMessages = new ArrayList<MessageDTO>();
		
		if (messages.isPresent()) {
			
			for (Message m : messages.get()) {
				
				dtoMessages.add(dtoMessageConverter.convertToDTO(m));			
			}
			
			return dtoMessages;			
		}
			
		return Collections.emptyList();
	}

	public MessageDTO openMessage(long id, MessageDTO message) {
		
		Optional<Message> foundMessage = messageRepository.findById(id);
		
		if(!foundMessage.isPresent()) {
			return new MessageDTO();
		}
		
				
		foundMessage.get().setLastUpdated(LocalDateTime.now());
		foundMessage.get().setOpened(true);
		
		messageRepository.save(foundMessage.get());
		
		
		return message;
	}
}
