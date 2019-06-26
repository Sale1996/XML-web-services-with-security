package com.tim9.accommodationservice.utils;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.tim9.reservationserviceClient.feignClients.ReservationClient;

import feign.FeignException;

@Component
public class DatasFromReservationMicroservice {

	ReservationClient reservationClient;
	
	public DatasFromReservationMicroservice(ReservationClient reservationClient) {
		this.reservationClient = reservationClient;
	}
	
	public List<Long> getAccommodationUnitIds(List<Long> accommodationIds, String dateFrom, String dateTo) {
		try {
			//reservationClient.getReservations();
			return reservationClient.getAccommodationUnitIdsForPeriod(accommodationIds, dateFrom, dateTo);
			//return Collections.emptyList();

		}
		catch(FeignException f) {
			return Collections.emptyList();
		}
		
	}
	
	public Boolean isThereActiveReservationForUnit(Long unitId) {
		try {
			return reservationClient.isThereActiveReservationForUnit(unitId);
		}
		catch(FeignException f) {
			return null;
		}
	}
	
	public Boolean deleteOccupanciesByUnit(Long unitid) {
		try {
			return reservationClient.deleteOccuppanciesByUnit(unitid);
		}
		catch(FeignException f) {
			return false;
		}
	}
}
