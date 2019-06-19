package com.tim9.agentapp.accommodation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.PriceLocal;


public interface PriceRepository extends JpaRepository<PriceLocal,Long> {

	List<PriceLocal> findAllByAccommodationUnitLocalAccommodationUnitId(Long id);

}
