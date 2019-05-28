package com.tim9.reservationservice.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.reservationservice.dtos.ReservationDTO;
import com.tim9.reservationservice.models.Reservation;
import com.tim9.reservationservice.repository.ReservationRepository;
import com.tim9.reservationservice.utils.dtoConverters.DTOReservationConverter;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	DTOReservationConverter reservationConverter;

	public List<ReservationDTO> findAll() {
		
		Optional< List<Reservation> > reservations = Optional.of ( reservationRepository.findAll() );
		
		ArrayList < ReservationDTO > dtoReservations = new ArrayList< ReservationDTO >();
		
		if ( reservations.isPresent() ) {
			for ( Reservation candidate : reservations.get() ) {
				dtoReservations.add(reservationConverter.convertToDTO(candidate));
			}
			return dtoReservations;
		}
			
		return Collections.emptyList();
	}
	
	public ReservationDTO findById(long id) {
		
		Optional< Reservation > reservation = reservationRepository.findById(id);
		
		if ( reservation.isPresent() ) {
			return reservationConverter.convertToDTO(reservation.get());
		}
		else {
			return new ReservationDTO();
		}
	}
	
	public ReservationDTO save(ReservationDTO reservation) {
		
		reservation.setReservationId(-1l);
		Reservation Reservation = reservationRepository.save(reservationConverter.convertFromDTO(reservation));
		reservation.setReservationId(Reservation.getReservationId());
		
		return reservation;
	
	}
	
	public ReservationDTO update(long id, ReservationDTO reservation) {
		
		Optional<Reservation> reservationForChange = reservationRepository.findById(id);
		
		if( reservationForChange.isPresent() && reservation!=null ) {
										
			reservationForChange.get().setAccommodationUnit(reservation.getAccommodationUnit());
			reservationForChange.get().setClient(reservation.getClient());
			reservationForChange.get().setConfirmation(reservation.isConfirmation());
			reservationForChange.get().setDateFrom(reservation.getDateFrom());
			reservationForChange.get().setDateTo(reservation.getDateTo());
			reservationForChange.get().setFinalPrice(reservation.getFinalPrice());
			
			reservationRepository.save(reservationForChange.get());
			reservation.setReservationId(reservationForChange.get().getReservationId());
			
			return reservation;
		}
		
		return new ReservationDTO();
	}
	
	public ReservationDTO delete(long id) {
		
		Optional<Reservation> reservationToDelete = reservationRepository.findById(id);
		
		if( reservationToDelete.isPresent() ) {
			reservationRepository.deleteById(id);
			
			return reservationConverter.convertToDTO(reservationToDelete.get());
		}
		
		return new ReservationDTO();
	}
	
}
