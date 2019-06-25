package com.tim9.agentapp.user.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.agentapp.user.dto.MessageDTO;
import com.tim9.agentapp.user.model.MessageLocal;
import com.tim9.agentapp.user.repository.MessageRepository;
import com.tim9.agentapp.user.soapclient.MessageClient;
import com.tim9.agentapp.user.utils.dtoConverter.DTOMessageConverter;
import com.tim9.agentapp.user.wsdl.CreateMessageResponse;
import com.tim9.agentapp.user.wsdl.GetMessagesResponse;
import com.tim9.agentapp.user.wsdl.Message;
import com.tim9.agentapp.user.wsdl.UpdateMessageResponse;

@Service
public class MessageService {
	
	private final MessageRepository messageRepository;
	
	private final DTOMessageConverter dtoMessageConverter;
	
	private final MessageClient messageClient;
	
	public MessageService(final MessageRepository messageRepository, final DTOMessageConverter dtoMessageConverter, final MessageClient messageClient) {
		this.messageRepository = messageRepository;
		this.dtoMessageConverter = dtoMessageConverter;
		this.messageClient = messageClient;
	}
	
	public MessageDTO findById(long id){
		
		Optional<MessageLocal> message = messageRepository.findById(id);
		
		if (message.isPresent()) {
			
			return dtoMessageConverter.convertToDTO(message.get());	
		}
		
		return new MessageDTO();		
	}
	
	public MessageDTO save(MessageDTO message){
		
//		message.setMessageId(-1l);
//		
//		MessageLocal m = dtoMessageConverter.converFromDTO(message);
//		m.setLastUpdated(LocalDateTime.now());
//				
//		m = messageRepository.save(m);
//		
//		message.setMessageId(m.getMessageId());
//		
//		return message;
		CreateMessageResponse response = messageClient.createMessage(dtoMessageConverter.convertFromDTOToWsdl(message));
		
		if(response.getMessage().getMessageId() != 0) {
			return dtoMessageConverter.convertToDTOFromClient(response.getMessage());
		}
		
		return new MessageDTO();
	}
	
	public List<MessageDTO> getAllMessagesOrderedByLatest(){
		
		Optional< List<MessageLocal> > messages = Optional.of(messageRepository.getAllMessagesOrderedByLatest());
		
		ArrayList<MessageDTO> dtoMessages = new ArrayList<MessageDTO>();
		
		if (messages.isPresent()) {
			
			for (MessageLocal m : messages.get()) {
				
				dtoMessages.add(dtoMessageConverter.convertToDTO(m));			
			}
			
			return dtoMessages;			
		}
			
		return Collections.emptyList();
	}
	
	public List<MessageDTO> getAllMessages(Long reservationId){
		
//		Optional< List<Message> > messages = Optional.of(messageRepository.getAllMessagesOrderedByLatest());
//		
//		
//		if (messages.isPresent()) {
//			
//			for (Message m : messages.get()) {
//				
//				dtoMessages.add(dtoMessageConverter.convertToDTO(m));			
//			}
//			
//			return dtoMessages;			
//		}
//			
//		return Collections.emptyList();
		
		ArrayList<MessageDTO> dtoMessages = new ArrayList<MessageDTO>();
		
		GetMessagesResponse response = messageClient.getMessages(reservationId);
		
		if (!response.getMessages().isEmpty()) {
		
			for (Message m : response.getMessages()) {
				
				dtoMessages.add(dtoMessageConverter.convertToDTOFromClient(m));			
			}
		
			return dtoMessages;			
		}
		
		return Collections.emptyList();
	}
	


	public MessageDTO openMessage(long id, MessageDTO message) {
		
//		Optional<MessageLocal> foundMessage = messageRepository.findById(id);
//		
//		if(!foundMessage.isPresent()) {
//			return new MessageDTO();
//		}
//		
//				
//		foundMessage.get().setLastUpdated(LocalDateTime.now());
//		foundMessage.get().setOpened(true);
//		
//		messageRepository.save(foundMessage.get());
//		
//		
//		return message;
		message.setOpened(true);
		UpdateMessageResponse response = messageClient.updateMessage(dtoMessageConverter.convertFromDTOToWsdl(message));
		
		if(response.getMessage().getMessageId() != 0) {
			return dtoMessageConverter.convertToDTOFromClient(response.getMessage());
		}
		
		return new MessageDTO();
	}
}
