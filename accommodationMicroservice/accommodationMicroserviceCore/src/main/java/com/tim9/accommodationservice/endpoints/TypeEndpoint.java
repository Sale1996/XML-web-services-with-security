package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetTypeRequest;
import com.tim9.accommodationservice.models.GetTypeResponse;
import com.tim9.accommodationservice.services.TypeService;

@Endpoint
public class TypeEndpoint {
	
	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private TypeService typeService;
	
	@Autowired
	public TypeEndpoint(TypeService typeService) {
		this.typeService = typeService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTypeRequest")
	@ResponsePayload
	public GetTypeResponse getPrices(@RequestPayload GetTypeRequest request) {
		GetTypeResponse response = new GetTypeResponse();
		response.setType(typeService.findAllSoap());
		return response;
	}
	
}
