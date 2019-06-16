package com.tim9.reservationservice.utils;

import org.springframework.stereotype.Component;

import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;
import com.tim9.accommodationserviceclient.feignClients.AccommodationUnitClient;

import feign.FeignException;

@Component
public class DatasFromAccommodationMicroservice {

	private AccommodationUnitClient accommodationUnitClient;
	
	public DatasFromAccommodationMicroservice(AccommodationUnitClient accommodationUnitClient) {
		this.accommodationUnitClient = accommodationUnitClient;
	}
	
	public AccommodationUnitDTO getById(Long id) {
		
		try {
			return accommodationUnitClient.getOneAccommodationUnitById(id);
		}
		catch(FeignException f) {			
			return new AccommodationUnitDTO();
		}
	}
}
