package com.tim9.reservationservice.utils.dtoConverters;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.reservationserviceClient.dtos.ReservationDTO;
import com.tim9.reservationservice.models.Reservation;
import com.tim9.reservationservice.repository.ReservationRepository;

@Component
public class DTOReservationConverter {

	@Autowired
	public ReservationRepository reservationRepository;
	
	public ReservationDTO convertToDTO (Reservation reservation) {
		
		ReservationDTO dto = new ReservationDTO();
		
		dto.setReservationId(reservation.getReservationId());
		dto.setAccommodationUnit(reservation.getAccommodationUnit());
		dto.setClient(reservation.getClient());
		dto.setConfirmation(reservation.isConfirmation());
		dto.setDateFrom(reservation.getDateFrom().toString());
		dto.setDateTo(reservation.getDateTo().toString());
		dto.setFinalPrice(reservation.getFinalPrice());
		
		return dto;
		
	}
	
	public Reservation convertFromDTO( ReservationDTO dto ) {
		
		Optional<Reservation> reservation = reservationRepository.findById(dto.getReservationId());
		
		if(reservation.isPresent()) {
			
			return reservation.get();
			
		}
		
		Reservation newBean = new Reservation();
		
		newBean.setReservationId(dto.getReservationId());
		newBean.setAccommodationUnit(dto.getAccommodationUnit());
		newBean.setClient(dto.getClient());
		newBean.setConfirmation(dto.isConfirmation());
		newBean.setDateFrom(LocalDateTime.parse(dto.getDateFrom()));
		newBean.setDateTo(LocalDateTime.parse(dto.getDateTo()));
		newBean.setFinalPrice(dto.getFinalPrice());
		
		return newBean;
		
	}
}
