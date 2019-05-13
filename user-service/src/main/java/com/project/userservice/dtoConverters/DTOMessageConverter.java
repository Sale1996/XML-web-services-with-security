package com.project.userservice.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.userservice.dtos.MessageDTO;
import com.project.userservice.models.Message;
import com.project.userservice.repositories.MessageRepository;

@Component
public class DTOMessageConverter {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public MessageDTO convertToDTO(Message message) {
		
		MessageDTO dto = new MessageDTO();
		
		dto.setMessageId(message.getMessageId());
		dto.setMessageBody(message.getMessageBody());
		dto.setMessageTime(message.getMessageTime());
		dto.setSenderId(message.getSenderId());
		dto.setRecieverId(message.getRecieverId());
		dto.setReservationId(message.getReservationId());

		return dto;
	}
	
	public Message converFromDTO(MessageDTO dto) {
		
		Optional<Message> message = messageRepository.findById(dto.getMessageId());
		
		if(message.isPresent()) {
			
			return message.get();
		}
		
		Message mes = new Message();
		
		mes.setMessageId(dto.getMessageId());
		mes.setMessageBody(dto.getMessageBody());
		mes.setMessageTime(dto.getMessageTime());
		mes.setSenderId(dto.getSenderId());
		mes.setRecieverId(dto.getRecieverId());
		mes.setReservationId(dto.getReservationId());
			
		return mes;
		
	}
}
