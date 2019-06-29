package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOAccommodationConverter;
import com.tim9.agentapp.accommodation.wsdl.Accommodation;
import com.tim9.agentapp.accommodation.wsdl.City;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationResponse;
import com.tim9.agentapp.accommodation.wsdl.UpdateAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.UpdateAccommodationResponse;
import com.tim9.agentapp.user.model.Bearer;
import com.tim9.agentapp.utils.HeaderClientInterceptor;
import com.tim9.agentapp.utils.TokenHeader;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationResponse;

public class AccommodationClient extends WebServiceGatewaySupport {
	
	@Autowired
	DTOAccommodationConverter accommodationConverter;

	public GetAccommodationResponse getAccommodation(String token, Long id) {
		GetAccommodationRequest request = new GetAccommodationRequest();
		request.setId(id);

		GetAccommodationResponse response = (GetAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreateAccommodationResponse createAccommodation(String token, Accommodation accommodation) {

		CreateAccommodationRequest request = new CreateAccommodationRequest();
		request.setAccommodation(accommodation);

		CreateAccommodationResponse response = (CreateAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new TokenHeader(
                                new Bearer(token)));

		return response;
	}
	
	public UpdateAccommodationResponse updateAccommodation(String token, Accommodation accommodation) {

		UpdateAccommodationRequest request = new UpdateAccommodationRequest();
		request.setAccommodation(accommodation);

		ClientInterceptor[] interceptors =
		        new ClientInterceptor[] {new HeaderClientInterceptor()};
		getWebServiceTemplate().setInterceptors(interceptors);
		
		UpdateAccommodationResponse response = (UpdateAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new TokenHeader(
                                new Bearer(token)));

		return response;
	}
}
