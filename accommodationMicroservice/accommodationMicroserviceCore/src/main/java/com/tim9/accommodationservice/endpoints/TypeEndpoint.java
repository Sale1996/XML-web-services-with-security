package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetTypesRequest;
import com.tim9.accommodationservice.models.GetTypesResponse;
import com.tim9.accommodationservice.services.TypeService;

@Endpoint
public class TypeEndpoint {
	
	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private TypeService typeService;
	
	@Autowired
	public TypeEndpoint(TypeService typeService) {
		this.typeService = typeService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTypesRequest")
	@ResponsePayload
	public GetTypesResponse getTypes(@RequestPayload GetTypesRequest request) {
		GetTypesResponse response = new GetTypesResponse();
		response.setType(typeService.findAllSoap());
		return response;
	}
	
}
