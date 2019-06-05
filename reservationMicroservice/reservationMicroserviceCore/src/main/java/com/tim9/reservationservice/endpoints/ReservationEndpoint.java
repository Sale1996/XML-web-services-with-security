package com.tim9.reservationservice.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.reservationservice.models.GetReservationsRequest;
import com.tim9.reservationservice.models.GetReservationsResponse;
import com.tim9.reservationservice.repository.ReservationRepository;

@Endpoint
public class ReservationEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/reservations";
	private ReservationRepository repository;
	
	@Autowired
	public ReservationEndpoint(ReservationRepository repository) {
		this.repository = repository;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationRequest")
	@ResponsePayload
	public GetReservationsResponse getReservations(@RequestPayload GetReservationsRequest request) {
		GetReservationsResponse response = new GetReservationsResponse();
		response.setReservations(repository.findAll());
		return response;
	}
}
