package com.tim9.userservice.utilss;

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
		try {
			return reservationClient.getReservationById(id);
		}
		catch(FeignException f) {
			return new ReservationDTO();
		}
	}
	
	
}
