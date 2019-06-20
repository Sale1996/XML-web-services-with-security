package com.tim9.agentapp.reservation.utils.dtoConverter;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tim9.agentapp.reservation.dto.ReservationDTO;
import com.tim9.agentapp.reservation.model.ReservationLocal;
import com.tim9.agentapp.reservation.repository.ReservationRepository;
import com.tim9.agentapp.reservation.wsdl.Reservation;

@Component
public class DTOReservationConverter {

	@Autowired
	public ReservationRepository reservationRepository;
	
	public ReservationDTO convertToDTO (ReservationLocal reservation) {
		
		ReservationDTO dto = new ReservationDTO();
		
		dto.setLocalReservationId(reservation.getLocalReservationId());
		dto.setReservationId(reservation.getLocalReservationId());
		dto.setAccommodationUnit(reservation.getAccommodationUnit());
		dto.setAccommodation(reservation.getAccommodation());
		dto.setClient(reservation.getClient());
		dto.setConfirmation(reservation.isConfirmation());
		dto.setDateFrom(reservation.getDateFrom().toString());
		dto.setDateTo(reservation.getDateTo().toString());
		dto.setFinalPrice(reservation.getFinalPrice());
		
		return dto;
		
	}
	
	public Reservation convertToWsdlFromLocal (ReservationLocal reservation) {
		
		Reservation dto = new Reservation();
		
		dto.setReservationId(reservation.getReservationId());
		dto.setAccommodationUnit(reservation.getAccommodationUnit());
		dto.setAccommodation(reservation.getAccommodation());
		dto.setClient(reservation.getClient());
		dto.setConfirmation(reservation.isConfirmation());
		dto.setDateFrom(reservation.getDateFrom().toString());
		dto.setDateTo(reservation.getDateTo().toString());
		dto.setFinalPrice(reservation.getFinalPrice());
		
		return dto;
		
	}
	
	public ReservationDTO convertToDTOFromClient (Reservation reservation) {
		
		ReservationDTO dto = new ReservationDTO();
		
		dto.setReservationId(reservation.getReservationId());
		dto.setAccommodationUnit(reservation.getAccommodationUnit());
		dto.setAccommodation(reservation.getAccommodation());
		dto.setClient(reservation.getClient());
		dto.setConfirmation(reservation.isConfirmation());
		dto.setDateFrom(reservation.getDateFrom().toString());
		dto.setDateTo(reservation.getDateTo().toString());
		dto.setFinalPrice(reservation.getFinalPrice());
		
		return dto;
		
	}
	
	public ReservationLocal convertFromDTO( ReservationDTO dto ) {
		
		if(dto.getReservationId() != null) {
			Optional<ReservationLocal> reservation = reservationRepository.findById(dto.getReservationId());
			if(reservation.isPresent()) {
				
				return reservation.get();
				
			}
		}
		
		ReservationLocal newBean = new ReservationLocal();
		
		newBean.setLocalReservationId(dto.getLocalReservationId());
		newBean.setReservationId(dto.getReservationId());
		newBean.setAccommodationUnit(dto.getAccommodationUnit());
		newBean.setAccommodation(dto.getAccommodation());
		newBean.setClient(dto.getClient());
		newBean.setConfirmation(dto.isConfirmation());
		newBean.setDateFrom(LocalDateTime.parse(dto.getDateFrom()));
		newBean.setDateTo(LocalDateTime.parse(dto.getDateTo()));
		newBean.setFinalPrice(dto.getFinalPrice());
		
		return newBean;
		
	}
}
