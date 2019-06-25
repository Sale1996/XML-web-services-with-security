package com.tim9.reservationservice.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.tim9.accommodationserviceclient.dtos.AccommodationDTO;
import com.tim9.accommodationserviceclient.dtos.AccommodationUnitDTO;
import com.tim9.reservationservice.models.Reservation;
import com.tim9.reservationservice.repository.ReservationRepository;
import com.tim9.reservationservice.utils.DatasFromAccommodationMicroservice;
import com.tim9.reservationservice.utils.DatasFromUserMicroservice;
import com.tim9.reservationservice.utils.dtoConverters.DTOReservationConverter;
import com.tim9.reservationserviceClient.dtos.ReservationDTO;
import com.tim9.userserviceClient.dtos.UserDTO;

@Service
public class ReservationService {

	private ReservationRepository reservationRepository;
	private DTOReservationConverter reservationConverter;
	private DatasFromUserMicroservice userData;
	private DatasFromAccommodationMicroservice accommData;
	
	public ReservationService(ReservationRepository reservationRepository, DTOReservationConverter reservationConverter, DatasFromUserMicroservice userData, DatasFromAccommodationMicroservice accommData) {
		this.reservationRepository = reservationRepository;
		this.reservationConverter = reservationConverter;
		this.accommData = accommData;
		this.userData = userData;
	}

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
		
		AccommodationUnitDTO unit = accommData.getUnitById(reservation.getAccommodationUnit());
		
		if(reservation.getClient() != 0l) {			
			UserDTO client = userData.getById(reservation.getClient());
			if(client.getId() == null) {
				return new ReservationDTO();
			}
		}
		
		//proveri da li ima vec neka rezervacija za isti taj unit u tom vremenskom periodu..
		Optional<Reservation> rezervacija = reservationRepository.checkIfAccommodationUnitIsFreeForPeriod(reservation.getAccommodationUnit(), reservation.getDateFrom(), reservation.getDateTo());

		
		if(unit.getAccommodationUnitId() == null || rezervacija.isPresent()) {

			return new ReservationDTO();
		}
		
		
		reservation.setReservationId(-1l);
		Reservation Reservation = reservationConverter.convertFromDTO(reservation);
		Reservation.setLastUpdated(LocalDateTime.now());

		Reservation.setAccommodation(unit.getAccomodation().getAccommodationId());
		Reservation = reservationRepository.save(Reservation);
		
		reservation.setReservationId(Reservation.getReservationId());
		
		return reservation;
	
	}
	
	public Reservation createOccupancy(Reservation reservation) {
		
		if(reservation.getClient() == 0l && reservation.getFinalPrice() == 0f) {
			
			AccommodationUnitDTO unit = accommData.getUnitById(reservation.getAccommodationUnit());
			
			if(reservation.getClient() != 0l) {			
				UserDTO client = userData.getById(reservation.getClient());
				if(client.getId() == null) {
					return new Reservation();
				}
			}

			//proveri da li ima vec neka rezervacija za isti taj unit u tom vremenskom periodu..
			Optional<Reservation> rezervacija = reservationRepository.checkIfAccommodationUnitIsFreeForPeriod(reservation.getAccommodationUnit(), reservation.getDateFrom().toString(), reservation.getDateTo().toString());
			
			
			if(unit.getAccommodationUnitId() == null || rezervacija.isPresent()) {
				
				return new Reservation();
			}
			
			
			reservation.setReservationId(-1l);
			reservation.setLastUpdated(LocalDateTime.now());
			reservation.setAccommodation(unit.getAccomodation().getAccommodationId());
			reservation = reservationRepository.save(reservation);
			
			reservation.setReservationId(reservation.getReservationId());
			
			return reservation;
		}

		return new Reservation();
	
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
		
		if( reservationToDelete.isPresent()) {
			AccommodationDTO accommDTO = accommData.getAccommodationById(reservationToDelete.get().getAccommodation());
			
			if (accommDTO.getNumberOfDaysBeforeCancelation() >= ChronoUnit.DAYS.between(reservationToDelete.get().getDateFrom(), LocalDateTime.now())) {
				
				reservationRepository.deleteById(id);
				return reservationConverter.convertToDTO(reservationToDelete.get());
			}
		}
		
		return new ReservationDTO();
	}
	
	public List<Long> getAccommodationUnitIdsForPeriod(List<Long> accommodationIds, String dateFrom, String dateTo) {
		
		return reservationRepository.findAccommodationUnitIds(accommodationIds, LocalDateTime.parse(dateFrom), LocalDateTime.parse(dateTo));
	}

	public List<Long> getAccommodationClients(Long accommodationId) {
		Optional<List<Long>> userIds = reservationRepository.getAccommodationClients(accommodationId);
		
		if(userIds.isPresent()) {
			return userIds.get();
		}
		
		return Collections.emptyList();
	}

	public List<ReservationDTO> findByClient(Long id) {
		
		Optional< List<Reservation> > reservations = Optional.of ( reservationRepository.findByClient(id) );
		
		ArrayList < ReservationDTO > dtoReservations = new ArrayList< ReservationDTO >();
		
		if ( reservations.isPresent() ) {
			for ( Reservation candidate : reservations.get() ) {
				dtoReservations.add(reservationConverter.convertToDTO(candidate));
			}
			return dtoReservations;
		}
			
		return Collections.emptyList();
	}
	
	public Reservation confirmReservation(Long id) {
		
		Optional< Reservation > reservation = reservationRepository.findById(id) ;
		
		if ( reservation.isPresent() ) {
			// check if isConfirmation set to TRUE and check if it's valid change regarding dateFrom and current time
			if(LocalDateTime.now().isAfter(reservation.get().getDateFrom())) {				
				reservation.get().setConfirmation(true);
				reservation.get().setLastUpdated(LocalDateTime.now());
				reservationRepository.save(reservation.get());
				return reservation.get();
			}
		}
			
		return new Reservation();
	}

	public List<Long> findByAccommodation(Long reservationId) {

		Optional< List<Long> > reservations = Optional.of ( reservationRepository.findIdsByAccommodation(reservationId) );
			
		if(!reservations.isPresent()) {
			return Collections.emptyList();
		}
		
		return reservations.get();
	}
}
