package com.tim9.reservationservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tim9.reservationservice.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	
	public List<Reservation> findByClient(Long id);
	
	public List<Reservation> findByAccommodation(long id);
	
	@Query(value = "SELECT reservation_id FROM reservations.reservation where accommodation = ?1", nativeQuery = true)
	public List<Long> findIdsByAccommodation(Long id);
	
	@Query(value = "SELECT accommodation_unit FROM reservations.reservation where accommodation in ?1 and \r\n" + 
			" \r\n" + 
			" ((date_from < ?2 and date_to > ?2) or \r\n" + 
			" (date_from > ?2 and date_to < ?3) or \r\n" + 
			"  \r\n" + 
			" (date_from < ?3 and date_to > ?3))", nativeQuery = true)
	public List<Long> findAccommodationUnitIds(List<Long> accommodationIds, LocalDateTime dateFrom, LocalDateTime dateTo);

	@Query(value = "SELECT * FROM reservations.reservation where accommodation_unit= ?1 and \r\n" + 
			" \r\n" + 
			" ((date_from <= ?2 and date_to >= ?2) or \r\n" + 
			" (date_from >= ?2 and date_to <= ?3) or \r\n" + 
			"  \r\n" + 
			" (date_from <= ?3 and date_to >= ?3))", nativeQuery = true)
	public Optional<Reservation> checkIfAccommodationUnitIsFreeForPeriod(Long accommodationUnit, String dateFrom, String dateTo);

	@Query(value = " SELECT distinct reservations.reservation.client FROM reservations.reservation where accommodation= ?1 ", nativeQuery = true)
	public Optional<List<Long>> getAccommodationClients(Long accommodationId);

	
	@Query(value = "SELECT * FROM reservations.reservation where accommodation_unit = ?1 and date_to >= ?2 " , nativeQuery = true)
	public Optional<List<Reservation>> isThereActiveReservationsForUnit(Long id, LocalDateTime now);

	
	public List<Reservation> findAllByAccommodationUnitAndClientAndFinalPrice(Long id, Long i, Float j);
	
	
}
