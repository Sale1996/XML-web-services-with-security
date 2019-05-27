package com.tim9.accommodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.accommodationservice.models.Accommodation;
import com.tim9.accommodationserviceclient.dtos.AccommodationSearchDTO;


public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {

	@Query(value = "SELECT accommodation as accommodationId, sum(number_of_people) as numberOfBeds FROM accommodation.accomodation_units "
	+"where accommodation in ("
		+"	SELECT id FROM accommodation.acccomodations where city = ?1"
	+"	)"
	+"	group by accommodation;", nativeQuery = true)
	public List<AccommodationSearchDTO> searchAccommodations(Long city, int numberOfGuests);
}
