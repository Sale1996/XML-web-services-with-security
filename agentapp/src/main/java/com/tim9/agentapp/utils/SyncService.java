package com.tim9.agentapp.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.accommodation.service.PriceService;
import com.tim9.agentapp.accommodation.soapclient.AccommodationClient;
import com.tim9.agentapp.accommodation.soapclient.AccommodationUnitClient;
import com.tim9.agentapp.accommodation.soapclient.PriceClient;
import com.tim9.agentapp.accommodation.wsdl.GetPricesResponse;
import com.tim9.agentapp.reservation.dto.ReservationDTO;
import com.tim9.agentapp.reservation.service.ReservationService;
import com.tim9.agentapp.reservation.soapclient.ReservationClient;
import com.tim9.agentapp.reservation.utils.dtoConverter.DTOReservationConverter;
import com.tim9.agentapp.reservation.wsdl.GetReservationsResponseAgent;
import com.tim9.agentapp.reservation.wsdl.Reservation;

@Service
public class SyncService {
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private ReservationService reservationService;
	
	public Boolean sync() {
		
		// input: agentId
		reservationService.syncReservations(2l);
//		priceService.sync(2l);
		
		return true;
	}
	
	// first, login agent & fetch details
	
	// input: agentId
//	private void syncAccommodation(Long id) {
//		GetAccommodationResponse response = accommodationClient.getAccommodation(id);
//	}
//	
//	// input: agentId
//	private void syncAccommodationUnits(Long id) {
//		GetAccommodationUnitsResponse response = accommodationUnitClient.getAccommodationUnits(id);
//	}
	
	// input: accommodationId
	private void syncPrices(Long id) {
//		GetPricesResponse response = priceClient.GetPrices(id);
	}
}
