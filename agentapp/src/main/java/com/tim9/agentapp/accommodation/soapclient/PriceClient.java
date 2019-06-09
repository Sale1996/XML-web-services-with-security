package com.tim9.agentapp.accommodation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.accommodation.wsdl.Price;
import com.tim9.agentapp.accommodation.wsdl.CreatePriceResponse;
import com.tim9.agentapp.accommodation.wsdl.DeletePriceRequest;
import com.tim9.agentapp.accommodation.wsdl.DeletePriceResponse;
import com.tim9.agentapp.accommodation.wsdl.GetPriceRequests;
import com.tim9.agentapp.accommodation.wsdl.GetPriceResponse;

public class PriceClient extends WebServiceGatewaySupport {

	public GetPriceResponse getPrices(Long id) {

		GetPriceRequests request = new GetPriceRequests();
		request.setId(id);

		GetPriceResponse response = (GetPriceResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreatePriceResponse createPrice(Price price) {

		CreatePriceResponse request = new CreatePriceResponse();
		request.setPrice(price);

		CreatePriceResponse response = (CreatePriceResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public DeletePriceResponse deletePrice(Long id) {

		DeletePriceRequest request = new DeletePriceRequest();
		request.setId(id);

		DeletePriceResponse response = (DeletePriceResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}
