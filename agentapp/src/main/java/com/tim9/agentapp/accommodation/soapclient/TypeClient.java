package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.GetTypesRequest;
import com.tim9.agentapp.accommodation.wsdl.GetTypesResponse;

public class TypeClient extends WebServiceGatewaySupport {

	public GetTypesResponse GetTypess() {

		GetTypesRequest request = new GetTypesRequest();

		GetTypesResponse response = (GetTypesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}