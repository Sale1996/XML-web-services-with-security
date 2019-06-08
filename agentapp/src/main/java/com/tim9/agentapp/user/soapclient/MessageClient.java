package com.tim9.agentapp.user.soapclient;

import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.user.wsdl.GetMessagesRequest;
import com.tim9.agentapp.user.wsdl.GetMessagesResponse;

public class MessageClient extends WebServiceGatewaySupport {

	public GetMessagesResponse getMessages(Long reservationId) {
		GetMessagesRequest request = new GetMessagesRequest();
		request.setReservationId(reservationId);
		
		GetMessagesResponse response = (GetMessagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));
		
		return response;
	}
}
