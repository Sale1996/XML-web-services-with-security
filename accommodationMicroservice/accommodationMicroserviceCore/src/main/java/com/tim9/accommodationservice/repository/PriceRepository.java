package com.tim9.accommodationservice.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tim9.accommodationservice.models.Price;



public interface PriceRepository extends JpaRepository<Price,Long> {

	List<Price> findAllByAccommodationUnitAccommodationUnitId(Long id);

	Optional<Price> findByPriceId(Long id);

//	List<Price> findAllByAccommodationUnitAccommodationAccommodationId(Long id);

	@Query(value = " SELECT * FROM accommodation.prices where accommodation_unit in ?1 \r\n" + 
			" 		 and date_from <= ?2 and date_to > ?2 \r\n" + 
			" 	     order by accommodation_unit ", nativeQuery = true)
	public Optional<List<Price>> calculatePricesForUnitsForPeriod(List<Long> unitsIds, LocalDateTime dateFrom);
	
	@Query(value = "SELECT * FROM accommodation.prices where accommodation_unit= ?1 and\r\n" + 
			"	((date_from <= ?2 and date_to >= ?2) or\r\n" + 
			"	(date_from >= ?2 and date_to <= ?3) or\r\n" + 
			"	(date_from <= ?3 and date_to >= ?3))\r\n", nativeQuery = true)
	public Optional<Price> checkIfThereIsAlreadyPrice(Long accommodationUnit, LocalDate dateFrom, LocalDate dateTo);


	@Query(value ="SELECT * FROM accommodation.prices WHERE accommodation_unit = ?1 ", nativeQuery = true)
	public Optional<List<Price>> getAllPricesFromUnit(Long unitId);
}
