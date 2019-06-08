package com.tim9.agentapp.reservation.soapclient;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.tim9.agentapp.reservation.wsdl.CreateReservationResponse;
import com.tim9.agentapp.reservation.wsdl.CreateReservationsRequest;
import com.tim9.agentapp.reservation.wsdl.DeleteReservationRequest;
import com.tim9.agentapp.reservation.wsdl.DeleteReservationResponse;
import com.tim9.agentapp.reservation.wsdl.GetReservationsRequest;
import com.tim9.agentapp.reservation.wsdl.GetReservationsResponse;
import com.tim9.agentapp.reservation.wsdl.Reservation;
import com.tim9.agentapp.reservation.wsdl.UpdateReservationRequest;
import com.tim9.agentapp.reservation.wsdl.UpdateReservationResponse;

public class ReservationClient extends WebServiceGatewaySupport {

	public GetReservationsResponse getReservations(Long id) {

		GetReservationsRequest request = new GetReservationsRequest();
		request.setId(id);

		GetReservationsResponse response = (GetReservationsResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public CreateReservationResponse createReservation(com.tim9.agentapp.reservation.wsdl.Reservation accommodation) {

		CreateReservationsRequest request = new CreateReservationsRequest();
		request.setReservation(accommodation);

		CreateReservationResponse response = (CreateReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	
	public DeleteReservationResponse deleteReservation(Long id) {

		DeleteReservationRequest request = new DeleteReservationRequest();
		request.setId(id);

		DeleteReservationResponse response = (DeleteReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
	public UpdateReservationResponse updateReservation(Reservation reservation) {

		UpdateReservationRequest request = new UpdateReservationRequest();
		request.setReservation(reservation);

		UpdateReservationResponse response = (UpdateReservationResponse) getWebServiceTemplate()
				.marshalSendAndReceive(this.getDefaultUri(), request,
						new SoapActionCallback(""));

		return response;
	}
}
