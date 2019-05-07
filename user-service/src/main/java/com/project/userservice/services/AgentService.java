package com.project.userservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.userservice.dtos.AgentDTO;
import com.project.userservice.repositories.AgentRepository;

@Service
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	public List<AgentDTO> findAll(){
		
		return null;
	}
	
	public AgentDTO findById(long id){
		
		return null;
	}
	
	public List<AgentDTO> findByName(String username){
		
		return null;
	}
	
	public AgentDTO update(long id, AgentDTO agent){
		
		return null;
	}
	
	public AgentDTO save(AgentDTO agent){
		
		return null;
	}
	
	public AgentDTO delete(long id){
		
		return null;
	}
	
}
