package com.tim9.reservationservice.endpoints;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.tim9.reservationservice.models.CreateReservationRequest;
import com.tim9.reservationservice.models.CreateReservationResponse;
import com.tim9.reservationservice.models.DeleteReservationRequest;
import com.tim9.reservationservice.models.DeleteReservationResponse;
import com.tim9.reservationservice.models.GetReservationsRequest;
import com.tim9.reservationservice.models.GetReservationsResponse;
import com.tim9.reservationservice.models.Reservation;
import com.tim9.reservationservice.models.UpdateReservationRequest;
import com.tim9.reservationservice.models.UpdateReservationResponse;
import com.tim9.reservationservice.repository.ReservationRepository;

@Endpoint
public class ReservationEndpoint {

	private static final String NAMESPACE_URI = "http://tim9.com/reservations";
	private ReservationRepository repository;
	
	@Autowired
	public ReservationEndpoint(ReservationRepository repository) {
		this.repository = repository;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationsRequestClient")
	@ResponsePayload
	public GetReservationsResponse getReservationsForClient(@RequestPayload GetReservationsRequest request) {
		GetReservationsResponse response = new GetReservationsResponse();
		response.setReservations(repository.findByClient(request.getId()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationsRequestAgent")
	@ResponsePayload
	public GetReservationsResponse getReservationsForAgent(@RequestPayload GetReservationsRequest request) {
		GetReservationsResponse response = new GetReservationsResponse();
//		response.setReservations(repository.findByAccommodation(request.getId()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateReservationsRequest")
	@ResponsePayload
	public UpdateReservationResponse updateReservation(@RequestPayload UpdateReservationRequest request) {
		UpdateReservationResponse response = new UpdateReservationResponse();
		Reservation r = request.getReservation();
		r.setLastUpdated(LocalDateTime.now());
		response.setReservation(repository.save(r));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createReservationsRequest")
	@ResponsePayload
	public CreateReservationResponse createReservation(@RequestPayload CreateReservationRequest request) {
		CreateReservationResponse response = new CreateReservationResponse();
		Reservation r = request.getReservation();
		r.setLastUpdated(LocalDateTime.now());
		r.setReservationId(null);
		response.setReservation(repository.save(r));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteReservationRequest")
	@ResponsePayload
	public DeleteReservationResponse deleteReservation(@RequestPayload DeleteReservationRequest request) {
		DeleteReservationResponse response = new DeleteReservationResponse();
		response.setReservation(repository.findById(request.getId()).get());
		repository.deleteById(request.getId());
		return response;
	}
}
