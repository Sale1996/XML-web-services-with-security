package com.tim9.agentapp.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.agentapp.reservation.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
