package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetCitiesRequest;
import com.tim9.accommodationservice.models.GetCitiesResponse;
import com.tim9.accommodationservice.services.CityService;

@Endpoint
public class CityEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private CityService cityService;
	
	@Autowired
	public CityEndpoint(CityService cityService) {
		this.cityService = cityService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCitiesRequest")
	@ResponsePayload
	public GetCitiesResponse getCities(@RequestPayload GetCitiesRequest request) {
		GetCitiesResponse response = new GetCitiesResponse();
		response.setCities(cityService.findAllSoap());
		return response;
	}
}