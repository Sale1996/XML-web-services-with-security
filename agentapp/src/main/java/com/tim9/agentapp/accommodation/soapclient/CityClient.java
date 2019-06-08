package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.GetCitiesRequest;
import com.tim9.agentapp.accommodation.wsdl.GetCitiesResponse;

public class CityClient extends WebServiceGatewaySupport {

	public GetCitiesResponse getCities() {

		GetCitiesRequest request = new GetCitiesRequest();

		GetCitiesResponse response = (GetCitiesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}