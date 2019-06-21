package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.tim9.agentapp.accommodation.wsdl.GetTypesRequest;
import com.tim9.agentapp.accommodation.wsdl.GetTypesResponse;
import com.tim9.agentapp.user.model.Bearer;
import com.tim9.agentapp.utils.TokenHeader;

public class TypeClient extends WebServiceGatewaySupport {

	public GetTypesResponse GetTypes(String token) {

		GetTypesRequest request = new GetTypesRequest();

		GetTypesResponse response = (GetTypesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new TokenHeader(
                                new Bearer(token)));

		return response;
	}
}