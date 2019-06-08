package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.GetCategoriesRequest;
import com.tim9.agentapp.accommodation.wsdl.GetCategoriesResponse;

public class CategoryClient extends WebServiceGatewaySupport {

	public GetCategoriesResponse getCategories() {

		GetCategoriesRequest request = new GetCategoriesRequest();

		GetCategoriesResponse response = (GetCategoriesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}
