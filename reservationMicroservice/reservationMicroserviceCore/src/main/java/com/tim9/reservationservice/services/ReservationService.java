package com.tim9.reservationservice.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.tim9.reservationserviceClient.dtos.ReservationDTO;
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
		Reservation Reservation = reservationConverter.convertFromDTO(reservation);
		Reservation.setLastUpdated(LocalDateTime.now());
		Reservation = reservationRepository.save(Reservation);
		reservation.setReservationId(Reservation.getReservationId());
		
		return reservation;
	
	}
	
	public ReservationDTO update(long id, ReservationDTO reservation) {
		
		Optional<Reservation> reservationForChange = reservationRepository.findById(id);
		
		if( reservationForChange.isPresent() && reservation!=null ) {
										
			reservationForChange.get().setAccommodationUnit(reservation.getAccommodationUnit());
			reservationForChange.get().setClient(reservation.getClient());
			reservationForChange.get().setConfirmation(reservation.isConfirmation());
			reservationForChange.get().setDateFrom(LocalDateTime.parse(reservation.getDateFrom()));
			reservationForChange.get().setDateTo(LocalDateTime.parse(reservation.getDateTo()));
			reservationForChange.get().setFinalPrice(reservation.getFinalPrice());
			reservationForChange.get().setLastUpdated(LocalDateTime.now());
			
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
	
	public List<Long> getAccommodationUnitIdsForPeriod(List<Long> accommodationIds, String dateFrom, String dateTo) {
		
		return reservationRepository.findAccommodationUnitIds(accommodationIds, LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
	}
}
