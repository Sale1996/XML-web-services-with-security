package com.tim9.pkiapi.request.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.tim9.pkiapi.request.dto.RequestDTO;


@Service
public interface IRequestService {

	public RequestDTO findOneById ( Long id );
		
	public List<RequestDTO> findAll();
	
	public RequestDTO save ( RequestDTO requestToSave );
	
	public RequestDTO deleteById ( Long id );

	public RequestDTO acceptRequest(Long id);

	public RequestDTO declineRequest(Long id);
		
}
