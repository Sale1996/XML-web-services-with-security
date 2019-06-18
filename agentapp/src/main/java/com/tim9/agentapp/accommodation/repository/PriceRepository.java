package com.tim9.agentapp.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.Price;


public interface PriceRepository extends JpaRepository<Price,Long> {

	List<Price> findAllByAccommodationUnitLocalAccommodationUnitId(Long id);

}
