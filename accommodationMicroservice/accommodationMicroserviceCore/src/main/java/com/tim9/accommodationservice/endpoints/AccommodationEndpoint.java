package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.CreateAccommodationRequest;
import com.tim9.accommodationservice.models.CreateAccommodationResponse;
import com.tim9.accommodationservice.models.EditAccommodationRequest;
import com.tim9.accommodationservice.models.EditAccommodationResponse;
import com.tim9.accommodationservice.models.GetAccommodationRequest;
import com.tim9.accommodationservice.models.GetAccommodationResponse;
import com.tim9.accommodationservice.services.AccommodationService;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationConverter;

@Endpoint
public class AccommodationEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	
	private AccommodationService accommodationService;
	private DTOAccommodationConverter accommodationConverter;
	
	@Autowired
	public AccommodationEndpoint(AccommodationService accommodationService, DTOAccommodationConverter converter) {
		this.accommodationService = accommodationService;
		this.accommodationConverter = converter;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccommodationRequest")
	@ResponsePayload
	public GetAccommodationResponse getAccommodation(@RequestPayload GetAccommodationRequest request) {
		GetAccommodationResponse response = new GetAccommodationResponse();
		response.setAccommodation(accommodationConverter.convertFromDTO(accommodationService.findByAgentId(request.getId())));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAccommodationRequest")
	@ResponsePayload
	public CreateAccommodationResponse createAccommodation(@RequestPayload CreateAccommodationRequest request) {
		CreateAccommodationResponse response = new CreateAccommodationResponse();
		response.setAccommodation(accommodationConverter.convertFromDTO(accommodationService.save(accommodationConverter.convertToDTO(request.getAccommodation()))));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "editAccommodationRequest")
	@ResponsePayload
	public EditAccommodationResponse editAccommodation(@RequestPayload EditAccommodationRequest request) {
		EditAccommodationResponse response = new EditAccommodationResponse();
		response.setAccommodation(accommodationConverter.convertFromDTO(accommodationService.update(request.getAccommodation().getAccommodationId(),accommodationConverter.convertToDTO(request.getAccommodation()))));
		return response;
	}
	
	
}