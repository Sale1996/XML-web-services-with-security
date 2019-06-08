package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.GetTypeRequest;
import com.tim9.agentapp.accommodation.wsdl.GetTypeResponse;

public class TypeClient extends WebServiceGatewaySupport {

	public GetTypeResponse getTypes() {

		GetTypeRequest request = new GetTypeRequest();

		GetTypeResponse response = (GetTypeResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}