package com.tim9.agentapp.reservation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.agentapp.reservation.model.ReservationLocal;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationLocal,Long> {

	Optional<ReservationLocal> findByLocalReservationId(Long id);
	Optional<ReservationLocal> findByReservationId(Long id);
}
