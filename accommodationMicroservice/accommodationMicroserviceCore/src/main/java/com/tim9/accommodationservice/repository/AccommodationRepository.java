package com.tim9.accommodationservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.accommodationservice.models.Accommodation;
import com.tim9.accommodationserviceclient.dtos.AccommodationSearchDTO;


public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
/*
	@Query(value = "SELECT accommodation as accommodationId, sum(number_of_people) as numberOfBeds FROM accommodation.accomodation_units "
			+"where accommodation in ("
			+"	SELECT id FROM accommodation.acccomodations where city = ?1"
			+"	)"
			+"	group by accommodation;", nativeQuery = true)
*/
	/*
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
*/
			@Query(value = "select id from accommodation.acccomodations "
					+ " where city=?1 ", nativeQuery = true)
			public List<Long> findAccommodationIdsByCity(Long city);
					
			public Optional<Accommodation> findOneByAgentId(long id);
			
			@Query(value = "with capacities as (\r\n" + 
					"	SELECT *, sum(number_of_people) as capacity FROM accommodation.accomodation_units where accommodation in ?1 and id not in ?2\r\n" + 
					"	group by accommodation )\r\n" + 
					"	select * from accommodation.acccomodations, capacities where accommodation.acccomodations.id = capacities.accommodation and capacities.capacity >= ?1;", nativeQuery = true)
			public Optional<List<Accommodation>> searchAccommodations(List<Long> accommodations, List<Long> accommodationUnits, int numberOfGuests);
}
