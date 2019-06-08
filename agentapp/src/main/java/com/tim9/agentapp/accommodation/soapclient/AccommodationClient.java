package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.model.Accommodation;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationResponse;
import com.tim9.agentapp.accommodation.wsdl.EditAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.EditAccommodationResponse;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationResponse;

public class AccommodationClient extends WebServiceGatewaySupport {

	public GetAccommodationResponse getAccommodation(Long id) {

		GetAccommodationRequest request = new GetAccommodationRequest();
		request.setId(id);

		GetAccommodationResponse response = (GetAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreateAccommodationResponse createAccommodation(Accommodation accommodation) {

		CreateAccommodationRequest request = new CreateAccommodationRequest();
		request.setAccommodation(accommodation);

		CreateAccommodationResponse response = (CreateAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public EditAccommodationResponse updateAccommodation(Accommodation accommodation) {

		EditAccommodationRequest request = new EditAccommodationRequest();
		request.setAccommodation(accommodation);

		EditAccommodationResponse response = (EditAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}
