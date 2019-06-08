package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.GetExtraFieldsRequest;
import com.tim9.agentapp.accommodation.wsdl.GetExtraFieldsResponse;

public class ExtraFieldClient extends WebServiceGatewaySupport {

	public GetExtraFieldsResponse getExtraFields() {

		GetExtraFieldsRequest request = new GetExtraFieldsRequest();

		GetExtraFieldsResponse response = (GetExtraFieldsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}