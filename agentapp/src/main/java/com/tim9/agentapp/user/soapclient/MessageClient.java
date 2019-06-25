package com.tim9.agentapp.user.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.user.wsdl.CreateMessageRequest;
import com.tim9.agentapp.user.wsdl.CreateMessageResponse;
import com.tim9.agentapp.user.wsdl.GetMessagesRequest;
import com.tim9.agentapp.user.wsdl.GetMessagesResponse;
import com.tim9.agentapp.user.wsdl.Message;
import com.tim9.agentapp.user.wsdl.UpdateMessageRequest;
import com.tim9.agentapp.user.wsdl.UpdateMessageResponse;

public class MessageClient extends WebServiceGatewaySupport {

	public GetMessagesResponse getMessages(Long reservationId) {
		
		GetMessagesRequest request = new GetMessagesRequest();
		request.setReservationId(reservationId);
		
		GetMessagesResponse response = (GetMessagesResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));
		
		return response;
	}
	
	public CreateMessageResponse createMessage(Message message) {
		
		CreateMessageRequest request = new CreateMessageRequest();
		request.setMessage(message);
		
		CreateMessageResponse response = (CreateMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));
		
		return response;
	}
	
	public UpdateMessageResponse  updateMessage(Message message) {
		
		UpdateMessageRequest request = new UpdateMessageRequest();
		request.setMessage(message);
		
		UpdateMessageResponse response = (UpdateMessageResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));
		
		return response;
	}
}
