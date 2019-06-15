package com.tim9.accommodationservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.accommodationservice.models.Price;



public interface PriceRepository extends JpaRepository<Price,Long> {

	List<Price> findAllByAccommodationUnitAccommodationUnitId(Long id);

	Optional<Price> findByPriceId(Long id);

	List<Price> findAllByAccommodationUnitAccommodationAccommodationId(Long id);

}
