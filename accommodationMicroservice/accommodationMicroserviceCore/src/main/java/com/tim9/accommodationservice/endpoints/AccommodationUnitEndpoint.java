package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetAccommodationUnitsRequest;
import com.tim9.accommodationservice.models.GetAccommodationUnitsResponse;
import com.tim9.accommodationservice.services.AccommodationUnitService;

@Endpoint
public class AccommodationUnitEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private AccommodationUnitService accommodationUnitService;
	
	@Autowired
	public AccommodationUnitEndpoint(AccommodationUnitService accommodationUnitService) {
		this.accommodationUnitService = accommodationUnitService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAccommodationUnitsRequest")
	@ResponsePayload
	public GetAccommodationUnitsResponse getAccommodationUnits(@RequestPayload GetAccommodationUnitsRequest request) {
		GetAccommodationUnitsResponse response = new GetAccommodationUnitsResponse();
		response.setAccommodationUnits(accommodationUnitService.findAllByAccommodationAgentId(Long.parseLong(request.getId())));
		return response;
	}
}
