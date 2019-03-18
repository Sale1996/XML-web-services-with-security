package com.tim9.pkiapi.request.dto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.request.model.Request;
import com.tim9.pkiapi.request.repository.RequestRepository;

@Component
public class RequestDTOConverter {

	@Autowired
	RequestRepository requestRepository;
	
	public RequestDTO convertToDTO (Request request) {
		
		RequestDTO dto = new RequestDTO();
		
		dto.setCommonName(request.getCommonName());
		dto.setCountry(request.getCountry());
		dto.setEmail(request.getEmail());
		dto.setId(request.getId());
		dto.setLocality(request.getLocality());
		dto.setOrganisation(request.getOrganisation());
		dto.setOrganisationUnit(request.getOrganisationUnit());
		dto.setPublicKey(request.getPublicKey());
		dto.setState(request.getState());
		
		return dto;
		
	}
	
	public Request convertFromDTO (RequestDTO dto) {
		
		Optional<Request> request = requestRepository.findById(dto.getId());
		
		if(request.isPresent()) {
		
			return request.get();
		
		}
		
		Request newRequest= new Request();
		
		newRequest.setCommonName(dto.getCommonName());
		newRequest.setCountry(dto.getCountry());
		newRequest.setEmail(dto.getEmail());
		newRequest.setId(dto.getId());
		newRequest.setLocality(dto.getLocality());
		newRequest.setOrganisation(dto.getOrganisation());
		newRequest.setOrganisationUnit(dto.getOrganisationUnit());
		newRequest.setPublicKey(dto.getPublicKey());
		newRequest.setState(dto.getState());
		
		return newRequest;		
		
		
		
	}
	
	
}
