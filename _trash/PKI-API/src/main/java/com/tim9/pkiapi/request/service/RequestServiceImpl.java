package com.tim9.pkiapi.request.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.pkiapi.request.dto.RequestDTO;
import com.tim9.pkiapi.request.dto.RequestDTOConverter;
import com.tim9.pkiapi.request.model.Request;
import com.tim9.pkiapi.request.repository.RequestRepository;

@Component
public class RequestServiceImpl implements IRequestService {

	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	RequestDTOConverter requestConverter;
	
	@Override
	public RequestDTO findOneById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<RequestDTO> findAll() {
//		// TODO Auto-generated method stub
//		
//		return null;
//	}
	@Override
	public List<RequestDTO> findAll() {
		List<RequestDTO> requests = new ArrayList<RequestDTO>();
		
		for (Request r  : requestRepository.findAll()) {
			RequestDTO request = requestConverter.convertToDTO(r);
			requests.add(request);
 		}
		return requests;
	}

	@Override
	public RequestDTO save(RequestDTO requestToSave) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDTO deleteById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDTO acceptRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RequestDTO declineRequest(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
