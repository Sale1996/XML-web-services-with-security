package com.tim9.agentapp.user.service;

import org.springframework.stereotype.Service;

import com.tim9.agentapp.user.soapclient.MessageClient;

@Service
public class SyncService {

	private final MessageService messageService;
	private final MessageClient messageClient;
	
	public SyncService(final MessageService messageService, final MessageClient messageClient) {
		this.messageService = messageService;
		this.messageClient = messageClient;
	}
	
	public boolean sync() {
		
		// get all reservations
		// iterate to get all messages
//		messageClient.getMessages(reservationId);
//		messageService
		
		return true;
	}
}
