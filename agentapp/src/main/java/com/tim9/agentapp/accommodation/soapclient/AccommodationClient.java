package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.utils.dtoConverter.DTOAccommodationConverter;
import com.tim9.agentapp.accommodation.wsdl.Accommodation;
import com.tim9.agentapp.accommodation.wsdl.City;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.CreateAccommodationResponse;
import com.tim9.agentapp.accommodation.wsdl.EditAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.EditAccommodationResponse;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationRequest;
import com.tim9.agentapp.accommodation.wsdl.GetAccommodationResponse;

public class AccommodationClient extends WebServiceGatewaySupport {
	
	@Autowired
	DTOAccommodationConverter accommodationConverter;

	public GetAccommodationResponse getAccommodation(Long id) {

		GetAccommodationRequest request = new GetAccommodationRequest();
		request.setId(id);

		GetAccommodationResponse response = (GetAccommodationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreateAccommodationResponse createAccommodation( com.tim9.agentapp.accommodation.model.Accommodation accommodation) {

		CreateAccommodationRequest request = new CreateAccommodationRequest();
		request.setAccommodation(accommodationConverter.convertToWsdl(accommodation));
		City city = new City();
		city.setCityId(1l);
		city.setName("ahdahwd");
		city.setXCord(11.0);
		city.setYCord(21.0);
		request.getAccommodation().setCity(city);
		request.getAccommodation().setLastUpdated("2019-05-28T20:29:44");

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