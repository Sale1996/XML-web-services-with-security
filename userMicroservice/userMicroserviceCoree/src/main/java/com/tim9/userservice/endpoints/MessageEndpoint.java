package com.tim9.userservice.endpoints;

import java.time.LocalDateTime;
import java.util.List;

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
import com.tim9.userservice.services.MessageService;
import com.tim9.userservice.utils.DatasFromReservationMicroservice;

@Endpoint
public class MessageEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com";
	private MessageRepository messageRepository;
	private DatasFromReservationMicroservice datasFromReservation;
	private MessageService messageService;
	
	@Autowired
	public MessageEndpoint(MessageRepository messageRepository, DatasFromReservationMicroservice datasFromReservation, MessageService messageService) {
		this.messageRepository = messageRepository;
		this.datasFromReservation = datasFromReservation;
		this.messageService = messageService;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMessagesRequest")
	@ResponsePayload
	public GetMessagesResponse getMessages(@RequestPayload GetMessagesRequest request) {
		GetMessagesResponse response = new GetMessagesResponse();
		
		List<Long> reservationIds = datasFromReservation.getReservationsByAccommodation(request.getReservationId());
		List<Message> messages = messageRepository.getByReservations(reservationIds);

		response.setMessages(messages);

		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMessageRequest")
	@ResponsePayload
	public UpdateMessageResponse updateMessage(@RequestPayload UpdateMessageRequest request) {
		UpdateMessageResponse response = new UpdateMessageResponse();
		response.setMessage(messageService.markAsOpened(request.getMessage()));
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
