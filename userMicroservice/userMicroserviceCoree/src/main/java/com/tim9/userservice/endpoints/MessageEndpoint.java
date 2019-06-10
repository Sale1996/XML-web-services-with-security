package com.tim9.userservice.endpoints;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.userservice.models.CreateMessageRequest;
import com.tim9.userservice.models.CreateMessageResponse;
import com.tim9.userservice.models.GetMessagesRequest;
import com.tim9.userservice.models.GetMessagesResponse;
import com.tim9.userservice.models.Message;
import com.tim9.userservice.models.UpdateMessageRequest;
import com.tim9.userservice.models.UpdateMessageResponse;
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
		response.setMessages(messageRepository.findMessagesByReservationId(request.getReservationId()).get());
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMessageRequest")
	@ResponsePayload
	public UpdateMessageResponse updateMessage(@RequestPayload UpdateMessageRequest request) {
		UpdateMessageResponse response = new UpdateMessageResponse();
		Message m = request.getMessage();
		m.setLastUpdated(LocalDateTime.now());
		response.setMessage(messageRepository.save(m));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createMessageRequest")
	@ResponsePayload
	public CreateMessageResponse createMessage(@RequestPayload CreateMessageRequest request) {
		CreateMessageResponse response = new CreateMessageResponse();
		Message m = request.getMessage();
		m.setLastUpdated(LocalDateTime.now());
		m.setMessageId(null);
		response.setMessage(messageRepository.save(m));
		return response;
	}
}
