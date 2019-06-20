package com.tim9.agentapp.reservation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tim9.agentapp.reservation.model.ReservationLocal;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationLocal,Long> {

	Optional<ReservationLocal> findByLocalReservationId(Long id);
	Optional<ReservationLocal> findByReservationId(Long id);
	
	@Query(value = "SELECT * FROM agentlocal.reservations where accommodation_unit= ?1 and \r\n" + 
			" \r\n" + 
			" ((date_from <= ?2 and date_to >= ?2) or \r\n" + 
			" (date_from >= ?2 and date_to <= ?3) or \r\n" + 
			"  \r\n" + 
			" (date_from <= ?3 and date_to >= ?3))", nativeQuery = true)
	public Optional<ReservationLocal> checkIfAccommodationUnitIsFreeForPeriod(Long accommodationUnit, String dateFrom,
			String dateTo);
	
	@Query(value = "SELECT * FROM agentlocal.reservations where client= 0 and final_price = 0 ", nativeQuery = true)
	public Optional<List<ReservationLocal>> getOcupancies();
	
	@Query(value = "SELECT * FROM agentlocal.reservations where client != 0 ", nativeQuery = true)
	public Optional<List<ReservationLocal>> findReservations();
}
