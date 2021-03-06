package com.tim9.userservice.dtoConverters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.userservice.models.Message;
import com.tim9.userservice.repositories.MessageRepository;
import com.tim9.userserviceClient.dtos.MessageDTO;

@Component
public class DTOMessageConverter {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public MessageDTO convertToDTO(Message message) {
		
		MessageDTO dto = new MessageDTO();
		
		dto.setMessageId(message.getMessageId());
		dto.setMessageBody(message.getMessageBody());
		dto.setMessageTime(message.getMessageTime());
		dto.setUserId(message.getUserId());
		dto.setRecieved(message.isRecieved());
		dto.setOpened(message.isOpened());
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
		mes.setUserId(dto.getUserId());
		mes.setRecieved(dto.isRecieved());
		mes.setOpened(dto.isOpened());
		mes.setReservationId(dto.getReservationId());
			
		return mes;
		
	}
}
