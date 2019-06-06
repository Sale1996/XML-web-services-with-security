package com.tim9.reservationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.reservationservice.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
	public List<Reservation> findByClient(long id);
	// treba ispisati upit, ali tu se preplice i saletova baza pa je to jebacina
	public List<Reservation> findByAccommodation(long id);
}
