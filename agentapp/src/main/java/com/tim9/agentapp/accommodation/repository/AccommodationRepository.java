package com.tim9.agentapp.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.agentapp.accommodation.model.Accommodation;


public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
/*
	@Query(value = "SELECT accommodation as accommodationId, sum(number_of_people) as numberOfBeds FROM accommodation.accomodation_units "
			+"where accommodation in ("
			+"	SELECT id FROM accommodation.acccomodations where city = ?1"
			+"	)"
			+"	group by accommodation;", nativeQuery = true)
*/
	
					@Query(value = "with akom as ( \r\n" + 
							"			SELECT accommodation, sum(number_of_people) as countedNumberOfBeds FROM accommodation.accomodation_units \r\n" + 
							"			where accommodation in (\r\n" + 
							"				SELECT id FROM accommodation.acccomodations where city = ?1 \r\n" + 
							"			) \r\n" + 
							"			group by accommodation \r\n" + 
							"			) \r\n" + 
							"			select * from akom left outer join accommodation.acccomodations on akom.accommodation = accommodation.acccomodations.id "
							+ " where countedNumberOfBeds > ?2 ", nativeQuery = true)
			public List<Accommodation> searchAccommodations(Long city, int numberOfGuests);
}
