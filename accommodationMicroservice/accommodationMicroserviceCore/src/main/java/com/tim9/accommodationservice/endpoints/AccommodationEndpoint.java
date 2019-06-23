package com.tim9.accommodationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.server.endpoint.annotation.SoapHeader;

import com.tim9.accommodationservice.models.Bearer;
import com.tim9.accommodationservice.models.CreateAccommodationRequest;
import com.tim9.accommodationservice.models.CreateAccommodationResponse;
import com.tim9.accommodationservice.models.GetAccommodationRequest;
import com.tim9.accommodationservice.models.GetAccommodationResponse;
import com.tim9.accommodationservice.models.UpdateAccommodationRequest;
import com.tim9.accommodationservice.models.UpdateAccommodationResponse;
import com.tim9.accommodationservice.services.AccommodationService;
import com.tim9.accommodationservice.utils.TokenExtractor;
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
	public GetAccommodationResponse getAccommodation(@SoapHeader("{" + Bearer.AUTH_NS + "}Bearer") SoapHeaderElement bearerToken, @RequestPayload GetAccommodationRequest request) {
		GetAccommodationResponse response = new GetAccommodationResponse();
		response.setAccommodation(accommodationConverter.convertFromDTO(accommodationService.findByAgentId(request.getId())));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createAccommodationRequest")
	@ResponsePayload
	public CreateAccommodationResponse createAccommodation(@SoapHeader("{" + Bearer.AUTH_NS + "}Bearer") SoapHeaderElement bearerToken, @RequestPayload CreateAccommodationRequest request) {
		CreateAccommodationResponse response = new CreateAccommodationResponse();
		response.setAccommodation(accommodationConverter.convertFromDTO(accommodationService.save(TokenExtractor.getBearerToken(bearerToken), accommodationConverter.convertToDTO(request.getAccommodation()))));
		return response;
	}
	
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateAccommodationRequest")
	@ResponsePayload
	public UpdateAccommodationResponse updateAccommodation(@SoapHeader("{" + Bearer.AUTH_NS + "}Bearer") SoapHeaderElement bearerToken, @RequestPayload UpdateAccommodationRequest request) {
		UpdateAccommodationResponse response = new UpdateAccommodationResponse();
		response.setAccommodation(accommodationConverter.convertFromDTO(accommodationService.update(TokenExtractor.getBearerToken(bearerToken), request.getAccommodation().getAccommodationId(),accommodationConverter.convertToDTO(request.getAccommodation()))));
		return response;
	}
	
	
}