package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetAccommodationRequest;
import com.tim9.accommodationservice.models.GetAccommodationResponse;
import com.tim9.accommodationservice.services.AccommodationService;
import com.tim9.accommodationservice.utils.dtoConverters.DTOAccommodationConverter;

@Endpoint
public class AccommodationEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	
	private AccommodationService accommodationService;
	private DTOAccommodationConverter accommodaitonConverter;
	
	@Autowired
	public AccommodationEndpoint(AccommodationService accommodationService, DTOAccommodationConverter converter) {
		this.accommodationService = accommodationService;
		this.accommodaitonConverter = converter;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccommodationRequest")
	@ResponsePayload
	public GetAccommodationResponse getAccommodation(@RequestPayload GetAccommodationRequest request) {
		GetAccommodationResponse response = new GetAccommodationResponse();
		response.setAccommodation(accommodaitonConverter.convertFromDTO(accommodationService.findByAgentId(Long.parseLong(request.getId()))));
		return response;
	}
}