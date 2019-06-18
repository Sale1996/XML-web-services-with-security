package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.accommodationservice.models.GetExtraFieldsByUnitRequest;
import com.tim9.accommodationservice.models.GetExtraFieldsByUnitResponse;
import com.tim9.accommodationservice.models.GetExtraFieldsRequest;
import com.tim9.accommodationservice.models.GetExtraFieldsResponse;
import com.tim9.accommodationservice.services.ExtraFieldService;

@Endpoint
public class ExtraFieldEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/accommodationService";
	private ExtraFieldService extraFieldService;
	
	@Autowired
	public ExtraFieldEndpoint(ExtraFieldService extraFieldService) {
		this.extraFieldService = extraFieldService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExtraFieldsRequest")
	@ResponsePayload
	public GetExtraFieldsResponse getExtraFields(@RequestPayload GetExtraFieldsRequest request) {
		GetExtraFieldsResponse response = new GetExtraFieldsResponse();
		response.setExtraFields(extraFieldService.findAllSoap());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExtraFieldsByUnitRequest")
	@ResponsePayload
	public GetExtraFieldsByUnitResponse getExtraFieldsByUnit(@RequestPayload GetExtraFieldsByUnitRequest request) {
		GetExtraFieldsByUnitResponse response = new GetExtraFieldsByUnitResponse();
		response.setExtraFields(extraFieldService.findAllByUnit(request.getId()));
		return response;
	}
}