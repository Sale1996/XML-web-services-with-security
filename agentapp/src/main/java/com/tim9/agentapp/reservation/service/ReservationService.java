package com.tim9.agentapp.reservation.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tim9.agentapp.reservation.dto.ReservationDTO;
import com.tim9.agentapp.reservation.model.ReservationLocal;
import com.tim9.agentapp.reservation.repository.ReservationRepository;
import com.tim9.agentapp.reservation.soapclient.ReservationClient;
import com.tim9.agentapp.reservation.utils.dtoConverter.DTOReservationConverter;
import com.tim9.agentapp.reservation.wsdl.ConfirmReservationResponse;
import com.tim9.agentapp.reservation.wsdl.GetReservationsResponseAgent;
import com.tim9.agentapp.reservation.wsdl.Reservation;

@Service
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ReservationClient reservationClient;
	
	@Autowired
	DTOReservationConverter reservationConverter;

	public List<ReservationDTO> findAll() {
		
		Optional< List<ReservationLocal> > reservations = Optional.of ( reservationRepository.findAll() );
		
		ArrayList < ReservationDTO > dtoReservations = new ArrayList< ReservationDTO >();
		
		if ( reservations.isPresent() ) {
			for ( ReservationLocal candidate : reservations.get() ) {
				dtoReservations.add(reservationConverter.convertToDTO(candidate));
			}
			return dtoReservations;
		}
			
		return Collections.emptyList();
	}
	
	public ReservationDTO findById(long id) {
		
		Optional< ReservationLocal > reservation = reservationRepository.findById(id);
		
		if ( reservation.isPresent() ) {
			return reservationConverter.convertToDTO(reservation.get());
		}
		else {
			return new ReservationDTO();
		}
	}
	
	public ReservationDTO save(ReservationDTO reservation) {
		
		Optional<ReservationLocal> foundReservation = reservationRepository.findByReservationId(reservation.getReservationId());
		
		if( foundReservation.isPresent() ) {
			return update(foundReservation.get().getLocalReservationId(), reservation);
		}
		
		ReservationLocal Reservation = reservationConverter.convertFromDTO(reservation);
		Reservation = reservationRepository.save(Reservation);
		reservation.setLocalReservationId(Reservation.getLocalReservationId());
		
		return reservation;
	
	}
	
	public ReservationDTO update(long id, ReservationDTO reservation) {
		
		Optional<ReservationLocal> reservationForChange = reservationRepository.findById(id);
		
		if( reservationForChange.isPresent() && reservation!=null ) {
										
			reservationForChange.get().setAccommodationUnit(reservation.getAccommodationUnit());
			reservationForChange.get().setClient(reservation.getClient());
			reservationForChange.get().setConfirmation(reservation.isConfirmation());
			reservationForChange.get().setDateFrom(LocalDateTime.parse(reservation.getDateFrom()));
			reservationForChange.get().setDateTo(LocalDateTime.parse(reservation.getDateTo()));
			reservationForChange.get().setFinalPrice(reservation.getFinalPrice());
			
			reservationRepository.save(reservationForChange.get());
			reservation.setReservationId(reservationForChange.get().getReservationId());
			
			return reservation;
		}
		
		return new ReservationDTO();
	}
	
	public Boolean confirmRealization(long id){
		
		Optional<ReservationLocal> reservationForChange = reservationRepository.findById(id);
		
		if(reservationForChange.isPresent()) {
			// TODO proveriti da li sme da potvrdi rezervaciju na osnovu trenutnog datuma
			if(LocalDateTime.now().isAfter(reservationForChange.get().getDateFrom())) {
				
				ConfirmReservationResponse response = reservationClient.confirmReservation(reservationForChange.get().getReservationId());
				if(response.getReservation().isConfirmation()) {
					reservationForChange.get().setConfirmation(true);
					reservationRepository.save(reservationForChange.get());
					return true;		
				}
			}		
		}
		
		return false;
	}
	
	public ReservationDTO delete(long id) {
		
		Optional<ReservationLocal> reservationToDelete = reservationRepository.findById(id);
		
		if( reservationToDelete.isPresent() ) {
			reservationRepository.deleteById(id);
			
			return reservationConverter.convertToDTO(reservationToDelete.get());
		}
		
		return new ReservationDTO();
	}
	
	// input: agentId
	public void syncReservations(Long id) {
		GetReservationsResponseAgent response = reservationClient.getReservationsByAgent(id);
		
		if(!response.getReservations().isEmpty()) {
			
			for ( Reservation reservation : response.getReservations() ) {
				save(reservationConverter.convertToDTOFromClient(reservation));
			}
		}
		
	}
	
}
