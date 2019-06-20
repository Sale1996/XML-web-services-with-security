package com.tim9.reservationservice.utils;

import org.springframework.stereotype.Component;

import com.tim9.accommodationserviceclient.dtos.AccommodationDTO;
import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;
import com.tim9.accommodationserviceclient.feignClients.AccommodationClient;
import com.tim9.accommodationserviceclient.feignClients.AccommodationUnitClient;

import feign.FeignException;

@Component
public class DatasFromAccommodationMicroservice {

	private AccommodationClient accommodationClient;
	private AccommodationUnitClient accommodationUnitClient;
	
	public DatasFromAccommodationMicroservice(AccommodationClient accommodationClient, AccommodationUnitClient accommodationUnitClient) {
		this.accommodationClient = accommodationClient;
		this.accommodationUnitClient = accommodationUnitClient;
	}
	
	public AccommodationUnitDTO getUnitById(Long id) {
		
		try {
			return accommodationUnitClient.getOneAccommodationUnitById(id);
		}
		catch(FeignException f) {			
			return new AccommodationUnitDTO();
		}
	}
	
	public AccommodationDTO getAccommodationById(Long id) {
		
		try {
			return accommodationClient.getAccommodationById(id);
		}
		catch(FeignException f) {
			return new AccommodationDTO();
		}
	}
}
