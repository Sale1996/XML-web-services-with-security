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
			@Query(value = "select id from acccomodations where if(-1!=?2, city in ( \r\n" + 
					"	SELECT id FROM accommodation.cities \r\n" + 
					"    where sqrt(pow(y_cord - (select y_cord from cities where id=?1 ),2) + \r\n" + 
					"    pow(x_cord - (select x_cord from cities where id=?1),2))< ?2), city = ?1 )", nativeQuery = true)
			public List<Long> findAccommodationIdsByCity(Long city, Integer distance);
					
			public Optional<Accommodation> findOneByAgentId(long id);
			
			@Query(value = "with capacities as ( \r\n " + 
					"	with tab as ( \r\n " + 
					"	SELECT accommodation_id, count(accommodation_id) as cnt \r\n " + 
					"    FROM accommodation.accommodation_unit_extra_fields \r\n " + 
					"    where extra_field_id in ?6 group by accommodation_id ) \r\n " + 
					"	SELECT *, sum(number_of_people) as capacity FROM accommodation.accomodation_units \r\n " + 
					"    where accommodation in ?1 and id not in ?2 and \r\n " + 
					"    IF( ?4 !=-1 , if(unit_type= ?4 ,1,0),1) and IF( ?5 != -1, if(unit_category = ?5 , 1 ,0),1) \r\n " + 
					"	and if( ?7 >0, accommodation.accomodation_units.id in ( select accommodation_id from tab  where cnt = ?7 ),1) \r\n " + 
					"    group by accommodation \r\n " + 
					") \r\n " + 
					"select * from accommodation.acccomodations, capacities where \r\n " + 
					"accommodation.acccomodations.id = capacities.accommodation \r\n " + 
					"and capacities.capacity >= ?3 ;", nativeQuery = true)
			public Optional<List<Accommodation>> searchAccommodations(List<Long> accommodations, List<Long> accommodationUnits, int numberOfGuests, Long type, Long category, List<Long> extraFields, int len);
}
