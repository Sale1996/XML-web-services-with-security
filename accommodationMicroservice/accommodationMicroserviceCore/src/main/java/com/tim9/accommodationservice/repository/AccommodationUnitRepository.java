package com.tim9.accommodationservice.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.accommodationservice.models.AccommodationUnit;


public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit,Long> {

	List<AccommodationUnit> findAllByAccommodationAgentId(long id);

	@Query(value = "with tab as ( \r\n" + 
			"				SELECT accommodation_id, count(accommodation_id) as cnt \r\n" + 
			"			    FROM accommodation.accommodation_unit_extra_fields \r\n" + 
			"			    where extra_field_id in ?5 group by accommodation_id \r\n" + 
			"                ) \r\n" + 
			"				SELECT * FROM accommodation.accomodation_units \r\n" + 
			"				where accommodation = ?1 and id not in ?2 and \r\n" + 
			"			    IF( ?3 !=-1 , if(unit_type= ?3 ,1,0),1) and IF( ?4 != -1, if(unit_category = ?4 , 1 ,0),1) \r\n" + 
			"				and if( ?6 >0, accommodation.accomodation_units.id in ( select accommodation_id from tab  where cnt = ?6 ),1) \r\n" +
			"				order by id ", nativeQuery = true)
	public Optional<List<AccommodationUnit>> searchAccommodationUnits(List<Long> accid, List<Long> accommodationUnits, Long type,
			Long category, List<Long> extraFields, int i);
}
