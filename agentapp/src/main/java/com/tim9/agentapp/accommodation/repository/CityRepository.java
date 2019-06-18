package com.tim9.agentapp.accommodation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tim9.agentapp.accommodation.model.CityLocal;

public interface CityRepository extends JpaRepository<CityLocal,Long> {

	Optional<CityLocal> findByName(String name);

}
