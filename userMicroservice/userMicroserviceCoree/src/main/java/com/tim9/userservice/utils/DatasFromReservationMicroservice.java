package com.tim9.userservice.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim9.reservationserviceClient.dtos.ReservationDTO;
import com.tim9.reservationserviceClient.feignClients.ReservationClient;

import feign.FeignException;

@Component
public class DatasFromReservationMicroservice {

	ReservationClient reservationClient;
	
	public DatasFromReservationMicroservice(ReservationClient reservationClient) {
		this.reservationClient = reservationClient;
	}
	
	
	public ReservationDTO getReservationById(Long id) {
		ReservationDTO reservation = null;
		try {
			reservation = reservationClient.getReservationById(id);
		}
		catch(FeignException f) {
			return new ReservationDTO();
		}
		return reservation;
	}
	
	public List<Long> getAccommodationClients(Long accommodationId) {
		List<Long> clients = new ArrayList<>();
		try {
			clients = reservationClient.getAccommodationClients(accommodationId);
		}
		catch(FeignException f) {
			return clients;
		}
		return clients;
	}
}
