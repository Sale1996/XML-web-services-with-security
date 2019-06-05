package com.tim9.accommodationservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tim9.accommodationservice.models.AccommodationUnit;


public interface AccommodationUnitRepository extends JpaRepository<AccommodationUnit,Long> {

	List<AccommodationUnit> findAllByAccommodationAgentId(long id);

}
