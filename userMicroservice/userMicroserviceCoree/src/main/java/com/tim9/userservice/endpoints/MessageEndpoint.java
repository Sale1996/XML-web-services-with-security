package com.tim9.userservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.userservice.models.GetMessagesRequest;
import com.tim9.userservice.models.GetMessagesResponse;
import com.tim9.userservice.repositories.MessageRepository;

@Endpoint
public class MessageEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com";
	private MessageRepository messageRepository;
	
	@Autowired
	public MessageEndpoint(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessagesRequest")
	@ResponsePayload
	public GetMessagesResponse getMessages(@RequestPayload GetMessagesRequest request) {
		GetMessagesResponse response = new GetMessagesResponse();
		response.setMessages(messageRepository.findMessagesByReservationId(Long.parseLong(request.getId())).get());
		//	response.setMessage(new Message());
		return response;
	}
}
