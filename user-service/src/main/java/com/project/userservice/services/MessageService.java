package com.project.userservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dtos.MessageDTO;
import com.project.userservice.repositories.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public List<MessageDTO> findAll(){
		
		return null;
	}
	
	public MessageDTO findById(long id){
		
		return null;
	}
	
	public List<MessageDTO> findByName(String username){
		
		return null;
	}
	
	public MessageDTO update(long id, MessageDTO message){
		
		return null;
	}
	
	public MessageDTO save(MessageDTO message){
		
		return null;
	}
	
	public MessageDTO delete(long id){
		
		return null;
	}
	
	
	

}
